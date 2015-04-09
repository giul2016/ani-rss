package danipix.anirss.rest.service;

import android.content.Context;
import android.os.AsyncTask;

import danipix.anirss.user.DbUserAdapter;
import danipix.anirss.user.User;
import danipix.anirss.user.UserFavorites;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 3/11/2015.
 */
public class HttpTask extends AsyncTask<Void, Integer, Void> {
    public static int counter;
    private DbUserAdapter dbUserAdapter;

    public HttpTask(Context context) {
        dbUserAdapter = DbUserAdapter.getInstance(context);
    }


    private OnProgressListener mOnProgressListener;

    private void getUserData() {
        RestClient.getInstance().getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                long userId = 0;
                if (user.getFavorites().size() > 0) {
                    userId = user.getFavorites().get(0).getUser_id();
                }
                String userName = user.getName();
                /* If the user already is in database, just update with new stuff (like user_id if the user has any favorites */
                dbUserAdapter.createUser(user, userId);

                for (UserFavorites userFavorites : user.getFavorites()) {
                    dbUserAdapter.createUserFavorites(userName, userFavorites);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    protected Void doInBackground(Void... params) {
        getUserData();
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressStart("User Module");
        }

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressStop("User Module");
            mOnProgressListener = null;
        }
    }


    public void setOnProgressListener(OnProgressListener onProgressListener) {
        mOnProgressListener = onProgressListener;
    }
}
