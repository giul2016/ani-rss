package danipix.anirss.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import danipix.anirss.DashboardActivity;
import danipix.anirss.R;
import danipix.anirss.constants.Constants;
import danipix.anirss.rest.service.SyncService;


public class LoginActivity extends ActionBarActivity {

    private LoginModule mLoginModule;
    private EditText email;
    private EditText password;
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        mSharedPreferences = getSharedPreferences(Constants.ANI_RSS, MODE_PRIVATE);

        if (!mSharedPreferences.getString(Constants.USER_EMAIL, "N/A").equals("N/A")) {
            Intent intent = new Intent(mContext, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login);
        OnLoginListener onLoginListener = new OnLoginListener() {
            @Override
            public void onLoginSuccessful(String email, String authToken) {
                mSharedPreferences.edit().putString(Constants.USER_EMAIL, email).apply();
                mSharedPreferences.edit().putString(Constants.USER_AUTH_TOKEN, authToken).apply();
                Intent intent = new Intent(mContext, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLoginFailed(String response) {
                Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServerNotResponding(String response) {
                Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
            }
        };

        mLoginModule = new LoginModule(onLoginListener);


        email = (EditText) findViewById(R.id.emailField);
        password = (EditText) findViewById(R.id.passwordField);
        Button signIn = (Button) findViewById(R.id.signInBtn);
        Button signUp = (Button) findViewById(R.id.signUpBtn);



        /*
        Sign In
         */
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn();

            }
        });

        /*
        Sign Up
         */
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });

    }


    private void userSignIn() {
        String strEmail = email.getText().toString();
        String strPassword = password.getText().toString();
        mLoginModule.generateAuthenticationToken(strEmail, strPassword);
        SyncService.startSync(LoginActivity.this);
    }

    private void userSignUp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://hummingbird.me/sign-up"));
        startActivity(intent);
    }
}
