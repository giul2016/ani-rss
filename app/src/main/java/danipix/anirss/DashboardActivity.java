package danipix.anirss;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import danipix.anirss.constants.Constants;
import danipix.anirss.rest.service.HttpTask;
import danipix.anirss.user.UserModule;


public class DashboardActivity extends ActionBarActivity {


    private boolean mSyncing = false;
    private boolean resumed = false;
    private ProgressDialog mProgressDialog;
    UserModule mUserModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.ANI_RSS, MODE_PRIVATE);
        String authToken = sharedPreferences.getString(Constants.USER_AUTH_TOKEN, "N/A");

        HttpTask httpTask = new HttpTask(DashboardActivity.this);
        httpTask.execute("GetUser");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
