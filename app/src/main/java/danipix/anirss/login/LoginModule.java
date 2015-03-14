package danipix.anirss.login;

import android.content.Context;

import com.google.gson.Gson;

import danipix.anirss.base.ModuleBase;
import danipix.anirss.rest.service.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class LoginModule extends ModuleBase{

    private Context mContext;
    private OnLoginListener mOnLoginListener;
    private Gson mGson;
    private static LoginModule instance;

    private LoginModule() {

    }

    public static LoginModule getInstance() {
        if (instance == null) {
            instance = new LoginModule();
        }
        return instance;
    }

    public LoginModule(Context context, OnLoginListener onLoginListener) {
        mContext = context;
        mOnLoginListener = onLoginListener;
    }

    public void generateAuthenticationToken(final String email, String password, Context context) {
        if (credentialsValidation(email, password)) {
            RestClient restClient = RestClient.getInstance();

            restClient.getApiService().authenticateUser(email, password, new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    mOnLoginListener.onLoginSuccessful(email, s);
                }

                @Override
                public void failure(RetrofitError error) {
                    mOnLoginListener.onLoginFailed(error.getMessage());
                }
            });


        } else {
            mOnLoginListener.onLoginFailed("Your credentials are not valid.");
        }
    }

    private boolean credentialsValidation(String email, String password) {
        return true;
    }
}
