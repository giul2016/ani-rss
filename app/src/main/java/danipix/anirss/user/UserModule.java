package danipix.anirss.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import danipix.anirss.rest.service.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 3/9/2015.
 */
public class UserModule {


    private static UserModule instance;
    private RestClient restClient;
    private Context mContext;

    public UserModule(Context context) {
        mContext = context;
    }


    public static UserModule getInstance(Context context) {
        if (instance == null) {
            instance = new UserModule(context);
        }
        return instance;
    }

    public void requestUserData(final String username) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                restClient = RestClient.getInstance();
                restClient.getApiService().getUserLibrary(username, new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        }, 10000);

    }

    public class requestUserData extends AsyncTask<Void, Void, Void> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            restClient = RestClient.getInstance();
            restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
                @Override
                public void success(User user, Response response) {

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            return null;
        }


    }
}
