package danipix.anirss.constants;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class AniRssItemsContract {

    public static final String AUTHORITY = "danipix.anirss";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public static final class UserInformation {

        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String NAME = "name";
        public static final String WAIFU = "waifu";
        public static final String WAIFU_OR_HUSBANDO = "waifu_or_husbando";
        public static final String WAIFU_SLUG = "waifu_slug";
        public static final String WAIFU_CHAR_ID = "waifu_char_id";
        public static final String LOCATION = "location";
        public static final String WEBSITE = "website";
        public static final String AVATAR = "avatar";
        public static final String COVER_IMAGE = "cover_image";
        public static final String ABOUT = "about";
        public static final String BIO = "bio";
        public static final String KARMA = "karma";
        public static final String LIFE_SPENT_ON_ANIME = "life_spent_on_anime";
        public static final String SHOW_ADULT_CONTENT = "show_adult_content";
        public static final String TITLE_LANGUAGE_PREFERENCE = "canonical";
        public static final String LAST_LIBRARY_UPDATE = "last_library_update";
        public static final String FOLLOWING = "following";
        public static final String FAVORITES = "favorites";

        public static final String USER_INFORMATION_TABLE = "user_information";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/danipix.anirss" + USER_INFORMATION_TABLE;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/danipix.anirss" + USER_INFORMATION_TABLE;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AniRssItemsContract.CONTENT_URI, USER_INFORMATION_TABLE);

        public static final String[] PROJECTION_ALL = {USER_ID, NAME, WAIFU, WAIFU_CHAR_ID, WAIFU_OR_HUSBANDO, WAIFU_SLUG,
                WAIFU_CHAR_ID, LOCATION, WEBSITE, AVATAR, COVER_IMAGE, ABOUT,
                BIO, KARMA, LIFE_SPENT_ON_ANIME, SHOW_ADULT_CONTENT, TITLE_LANGUAGE_PREFERENCE,
                LAST_LIBRARY_UPDATE, FOLLOWING, FAVORITES};

    }
}
