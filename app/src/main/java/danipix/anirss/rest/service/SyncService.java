package danipix.anirss.rest.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import danipix.anirss.rest.model.IntentConstants;
import danipix.anirss.user.UserModule;

/**
 * Created by Dani Pix on 3/7/2015.
 */
public class SyncService extends IntentService {

    private UserModule mUserModule;
    private static long running = 0;
    private LocalBroadcastManager mLocalBroadcastManager;

    public SyncService() {
        super("");
    }


    private OnProgressListener onProgressListener = new OnProgressListener() {
        @Override
        public void onProgressStart(String moduleName) {
            Intent localIntent = new Intent(IntentConstants.START_SYNC);
            mLocalBroadcastManager.sendBroadcast(localIntent);
            running++;
        }

        @Override
        public void onProgressFinnish(String moduleName) {
            running--;
            if (running == 0) {
                Intent localIntent = new Intent(IntentConstants.STOP_SYNC);
                mLocalBroadcastManager.sendBroadcast(localIntent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(SyncService.this);
        mUserModule = UserModule.getInstance(getApplicationContext());
        mUserModule.setOnProgressListener(onProgressListener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SERVICE - START", Thread.currentThread().getName());
        boolean syncUser = false;

        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                syncUser = extras.getBoolean(IntentConstants.SYNC_USER);
            }
        }

        if (syncUser) {
            mUserModule.requestUserData("DaniPix");

        }
    }

    public static void performSync(Context context) {
        Intent mServiceIntent = new Intent(context, SyncService.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IntentConstants.SYNC_USER, true);
        mServiceIntent.putExtras(bundle);
        context.startService(mServiceIntent);
    }
}
