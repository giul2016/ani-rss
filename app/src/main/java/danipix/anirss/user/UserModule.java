package danipix.anirss.user;

import android.content.Context;

import danipix.anirss.base.BaseModule;
import danipix.anirss.rest.service.HttpTask;
import danipix.anirss.rest.service.MyThreadPoolExecutor;

/**
 * Created by Dani Pix on 3/9/2015.
 */
public class UserModule extends BaseModule {


    private static UserModule instance;
    private Context mContext;


    public UserModule(Context context) {
        mContext = context;
        mModuleName = "User Module";
    }


    public static UserModule getInstance(Context context) {
        if (instance == null) {
            instance = new UserModule(context);
        }
        return instance;
    }


    public void requestUserData() {
        HttpTask httpTask = new HttpTask();
        httpTask.setOnProgressListener(mOnProgressListener);
        httpTask.executeOnExecutor(MyThreadPoolExecutor.getInstance(), (Void[]) null);
    }
}
