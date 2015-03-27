package danipix.anirss.rest.service;

import retrofit.RestAdapter;

/**
 * Created by Dani Pix on 3/6/2015.
 */
public class RestClient {
    private ApiService apiService;
    private static RestClient instance;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://hummingbird.me/api/v1")
                .build();
        apiService = restAdapter.create(ApiService.class);
    }


    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
