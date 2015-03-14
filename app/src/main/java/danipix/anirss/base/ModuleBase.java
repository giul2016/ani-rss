package danipix.anirss.base;

import danipix.anirss.rest.service.OnProgressListener;

/**
 * Created by Dani Pix on 3/9/2015.
 */
public abstract class ModuleBase implements OnProgressListener {
    protected OnProgressListener mOnProgressListener;
    protected String mModuleName = "Base Module";

    public void setOnProgressListener(OnProgressListener mOnProgressListener) {
        this.mOnProgressListener = mOnProgressListener;
    }

    @Override
    public void onProgressStart(String taskName) {
        String progressName = mModuleName;

        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressStart(progressName);
        }
    }

    @Override
    public void onProgressFinnish(String moduleName) {
        String progressName = mModuleName;

        if (mOnProgressListener != null) {
            mOnProgressListener.onProgressFinnish(progressName);
        }



    }
}
