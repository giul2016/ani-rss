package danipix.anirss.rest.service;

import android.os.AsyncTask;
import android.util.Log;

import danipix.anirss.DashboardActivity;
import danipix.anirss.user.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 3/11/2015.
 */
public class HttpTask extends AsyncTask<Void, Integer, Void> {
    private int numberOfTasks = 10;
    public static int counter;

    public static void refreshCounter(){
        counter = 0;
    }

    private OnProgressListener mOnProgressListener;
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
    protected Void doInBackground(Void... params) {
        numberOfTasks = SyncService.getNumberOfTasks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.getMessage();
        }
        Log.d("Thread", "Threading");

        //DashboardActivity.mProgressDialog.setProgress(((numberOfTasks + counter) - numberOfTasks));
        DashboardActivity.wheelProgressDialog.progress(((numberOfTasks + counter) - numberOfTasks));
        counter += 10;
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
