package danipix.anirss.rest.model;

/**
 * Created by Dani Pix on 3/6/2015.
 */
public interface Constants {

    public static final String SERVER_URL = "http://hummingbird.me/api/v1";

    /* Anime */
    public static final String GET_METADATA_BY_ID = SERVER_URL + "/anime/{id}";
    public static final String SEARCH_BY_TITLE = SERVER_URL + "/search/anime";

    /* Library */
    public static final String GET_ALL_ENTRIES = SERVER_URL + "/users/{username}/library";
    public static final String ADD_UPDATE_ENTRY = SERVER_URL + "/libraries/{id}";
    public static final String REMOVE_ENTRY = SERVER_URL + "/libraries/{id}/remove";

    /* User */
    public static final String AUTHENTICATE = SERVER_URL + "/users/authenticate";
    public static final String GET_INFORMATION = SERVER_URL + "/users/{username}";
    public static final String GET_ACTIVITY_FEED = SERVER_URL + "/users/{username}/feed";
    public static final String GET_FAVORITE_ANIME = SERVER_URL + "/users/{username}/favorite_anime";
}
