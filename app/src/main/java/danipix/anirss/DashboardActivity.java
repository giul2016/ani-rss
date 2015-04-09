package danipix.anirss;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import danipix.anirss.constants.Constants;
import danipix.anirss.rest.model.IntentConstants;
import danipix.anirss.rest.service.OnSyncListener;
import danipix.anirss.rest.service.SyncService;
import danipix.anirss.rest.service.SyncStatusReceiver;


public class DashboardActivity extends ActionBarActivity {

    private SyncStatusReceiver mSyncStatusReceiver;
    private boolean mSyncing = false;
    private boolean mResumed = false;
    public static ProgressDialog mProgressDialog;

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0, 0);
        mResumed = true;
        if (mSyncing) {
           mProgressDialog.show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        mResumed = false;
       mProgressDialog.dismiss();

    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter mStartIntentFilter = new IntentFilter(IntentConstants.START_SYNC);
        IntentFilter mStopIntentFilter = new IntentFilter(IntentConstants.STOP_SYNC);
        LocalBroadcastManager.getInstance(this).registerReceiver(mSyncStatusReceiver, mStartIntentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mSyncStatusReceiver, mStopIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mSyncStatusReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SyncService.startSync(DashboardActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.ANI_RSS, MODE_PRIVATE);
        String authToken = sharedPreferences.getString(Constants.USER_AUTH_TOKEN, "N/A");

        Button syncBtn = (Button) findViewById(R.id.syncBtn);
        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SyncService.startSync(DashboardActivity.this);
            }
        });

        mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setProgressNumberFormat("%1d");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);


        mSyncStatusReceiver = new SyncStatusReceiver(new OnSyncListener() {
            @Override
            public void onSyncStart() {
                mSyncing = true;
                if (mResumed) {
                    mProgressDialog.show();

                }
            }

            @Override
            public void onSyncStop() {
                mSyncing = false;
                if (mResumed) {
                   mProgressDialog.dismiss();

                }
            }
        });
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
