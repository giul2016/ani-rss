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
public class HttpTask extends AsyncTask<String, Void, Void> {

    private Context mContext;
    private ProgressDialog mProgressDialog;

    public HttpTask(Context context) {
        mContext = context;
    }
    RestClient restClient = RestClient.getInstance();



    private void getUserData1(){
        restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    private void getUserData2(){
        restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    private void getUserData3(){
        restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    private void getUserData4(){
        restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    private void getUserData5(){
        restClient.getApiService().getUserLibrary("DaniPix", new Callback<User>() {
            @Override
            public void success(User user, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    @Override
    protected Void doInBackground(String... params) {

        getUserData1();
        getUserData2();
        getUserData3();
        getUserData4();
        getUserData5();


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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mProgressDialog.dismiss();
    }
}
