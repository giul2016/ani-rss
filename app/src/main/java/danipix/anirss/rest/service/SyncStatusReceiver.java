package danipix.anirss.rest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import danipix.anirss.rest.model.IntentConstants;

/**
 * Created by Dani Pix on 3/9/2015.
 */
public class SyncStatusReceiver extends BroadcastReceiver {
    private final OnSyncListener mOnSyncListener;

    public SyncStatusReceiver(OnSyncListener onSyncListener) {
        mOnSyncListener = onSyncListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            if (action.equals(IntentConstants.START_SYNC)) {
                mOnSyncListener.onSyncStart();
            } else if (action.equals(IntentConstants.STOP_SYNC)) {
                mOnSyncListener.onSyncStop();
            }
        }
    }
}
