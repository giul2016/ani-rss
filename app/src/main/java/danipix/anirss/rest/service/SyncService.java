package danipix.anirss.rest.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import danipix.anirss.rest.model.IntentConstants;
import danipix.anirss.user.UserModule;

/**
 * Created by Dani Pix on 4/5/2015.
 */
public class SyncService extends IntentService {
    private LocalBroadcastManager mLocalBroadcastManager;
    public static int running = 0;
    UserModule mUserModule;
    public static boolean syncTriggeredFlag = false;

    public SyncService() {
        super("");
    }

    public static void refreshRunning(){
        running = 0;
    }

    public static int getNumberOfTasks(){
        return running;
    }

    private OnProgressListener onProgressListener = new OnProgressListener() {
        @Override
        public void onProgressStart(String moduleName) {
            Intent localIntent = new Intent(IntentConstants.START_SYNC);
            mLocalBroadcastManager.sendBroadcast(localIntent);
            running++;
        }

        @Override
        public void onProgressStop(String moduleName) {
            running--;
            if (running == 0) {
                Intent localIntent = new Intent(IntentConstants.STOP_SYNC);
                mLocalBroadcastManager.sendBroadcast(localIntent);
                HttpTask.refreshCounter();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(SyncService.this);
        mUserModule = UserModule.getInstance(this);
        mUserModule.setOnProgressListener(onProgressListener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean syncUserData = false;


        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                syncUserData = extras.getBoolean(IntentConstants.SYNC_USER_DATA);
            }


            if (syncUserData) {
                for(int i = 0; i < 10; i++){
                    mUserModule.requestUserData();
                }
            }
        }
    }


    public static void startSync(Context context) {
        syncTriggeredFlag = true;
        Intent serviceIntent = new Intent(context, SyncService.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IntentConstants.SYNC_USER_DATA, true);
        serviceIntent.putExtras(bundle);
        context.startService(serviceIntent);
    }

    public static void stopSync(Context context) {
        Intent serviceIntent = new Intent(context, SyncService.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IntentConstants.SYNC_USER_DATA, false);
        serviceIntent.putExtras(bundle);
        context.stopService(serviceIntent);
    }
}