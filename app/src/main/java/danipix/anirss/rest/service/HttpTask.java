package danipix.anirss.rest.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import danipix.anirss.user.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 3/11/2015.
 */
public class HttpTask extends AsyncTask<String, Void, RestClient> {

    private Context mContext;
    private ProgressDialog mProgressDialog;


    public HttpTask(Context context) {
        mContext = context;
    }


    private void getUserData() {
        RestClient.getInstance().getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    protected RestClient doInBackground(String... params) {

        if (params[0].equals("GetUser")) {
            getUserData();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
        mProgressDialog.setMessage("Synchronizing...");
        mProgressDialog.setCancelable(false);

    }

    @Override
    protected void onPostExecute(RestClient result) {
        super.onPostExecute(result);
        mProgressDialog.dismiss();
    }
}
