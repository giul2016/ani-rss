package danipix.anirss.rest.service;

import danipix.anirss.user.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Dani Pix on 3/6/2015.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("/users/authenticate")
    public void authenticateUser(@Field("email") String email, @Field("password") String password, Callback<String> callback);


    @GET("/users/{username}")
    public void getUserLibrary(@Path("username") String username, Callback<User> callback);


}
