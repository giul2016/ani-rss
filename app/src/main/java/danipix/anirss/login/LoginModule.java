package danipix.anirss.login;

import danipix.anirss.rest.service.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class LoginModule {

    private OnLoginListener mOnLoginListener;
    private static LoginModule instance;

    private LoginModule() {

    }

    public static LoginModule getInstance() {
        if (instance == null) {
            instance = new LoginModule();
        }
        return instance;
    }

    public LoginModule(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }

    public void generateAuthenticationToken(final String email, String password) {
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
