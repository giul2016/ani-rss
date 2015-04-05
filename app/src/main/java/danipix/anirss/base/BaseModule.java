package danipix.anirss.base;

import android.text.TextUtils;

import danipix.anirss.rest.service.OnProgressListener;

/**
 * Created by Dani Pix on 4/5/2015.
 */
public abstract class BaseModule implements OnProgressListener {
    protected OnProgressListener mOnProgressListener;
    protected String mModuleName = "Base Module";


    public void setOnProgressListener(OnProgressListener onProgressListener) {
        mOnProgressListener = onProgressListener;
    }

    @Override
    public void onProgressStart(String moduleName) {
        String progressName = mModuleName;
        if (!TextUtils.isEmpty(moduleName)) {
            progressName = mModuleName + " - " + moduleName;
        }
        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressStart(progressName);
        }
    }

    @Override
    public void onProgressStop(String moduleName) {
        String progressName = mModuleName;
        if (!TextUtils.isEmpty(moduleName)) {
            progressName = mModuleName + " - " + moduleName;
        }
        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressStop(progressName);
        }

    }
}
