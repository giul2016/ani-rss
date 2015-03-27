package danipix.anirss.user;

import android.content.Context;

import danipix.anirss.rest.service.RestClient;

/**
 * Created by Dani Pix on 3/9/2015.
 */
public class UserModule {


    private static UserModule instance;
    private RestClient restClient;
    private Context mContext;

    public UserModule(Context context) {
        mContext = context;
    }


    public static UserModule getInstance(Context context) {
        if (instance == null) {
            instance = new UserModule(context);
        }
        return instance;
    }
}
