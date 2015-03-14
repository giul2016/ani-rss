package danipix.anirss.login;

/**
 * Created by Dani Pix on 2/22/2015.
 */
public interface OnLoginListener {
    void onLoginSuccessful(String email, String authToken);
    void onLoginFailed(String response);
    void onServerNotResponding(String response);
}
