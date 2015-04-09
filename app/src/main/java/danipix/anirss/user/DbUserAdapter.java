package danipix.anirss.user;

import android.content.ContentValues;
import android.content.Context;

import danipix.anirss.constants.AniRssItemsContract;

/**
 * Created by Dani Pix on 4/9/2015.
 */
public class DbUserAdapter {

    private static DbUserAdapter instance;
    private Context mContext;

    public static DbUserAdapter getInstance(Context context) {
        if (instance == null) {
            instance = new DbUserAdapter(context);
        }
        return instance;
    }

    private DbUserAdapter(Context context) {
        mContext = context;
    }

    public void createUser(User user, long userId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AniRssItemsContract.UserInformation.USER_ID, userId);
        contentValues.put(AniRssItemsContract.UserInformation.NAME, user.getName());
        contentValues.put(AniRssItemsContract.UserInformation.WAIFU, user.getWaifu());
        contentValues.put(AniRssItemsContract.UserInformation.WAIFU_OR_HUSBANDO, user.getWaifu_or_husbando());
        contentValues.put(AniRssItemsContract.UserInformation.WAIFU_SLUG, user.getWaifu_slug());
        contentValues.put(AniRssItemsContract.UserInformation.WAIFU_CHAR_ID, user.getWaifu_char_id());
        contentValues.put(AniRssItemsContract.UserInformation.LOCATION, user.getLocation());
        contentValues.put(AniRssItemsContract.UserInformation.WEBSITE, user.getWebsite());
        contentValues.put(AniRssItemsContract.UserInformation.AVATAR, user.getAvatar());
        contentValues.put(AniRssItemsContract.UserInformation.COVER_IMAGE, user.getCover_image());
        contentValues.put(AniRssItemsContract.UserInformation.ABOUT, user.getAbout());
        contentValues.put(AniRssItemsContract.UserInformation.BIO, user.getBio());
        contentValues.put(AniRssItemsContract.UserInformation.KARMA, user.getKarma());
        contentValues.put(AniRssItemsContract.UserInformation.LIFE_SPENT_ON_ANIME, user.getLife_spent_on_anime());
        contentValues.put(AniRssItemsContract.UserInformation.SHOW_ADULT_CONTENT, user.isShow_adult_content());
        contentValues.put(AniRssItemsContract.UserInformation.TITLE_LANGUAGE_PREFERENCE, user.getTitle_language_preference());
        contentValues.put(AniRssItemsContract.UserInformation.LAST_LIBRARY_UPDATE, user.getLast_library_updated());
        contentValues.put(AniRssItemsContract.UserInformation.FOLLOWING, user.isFollowing());

        mContext.getContentResolver().insert(AniRssItemsContract.UserInformation.CONTENT_URI, contentValues);
    }


    public void createUserFavorites(String userName, UserFavorites userFavorites) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AniRssItemsContract.UserFavorites.USER_NAME, userName);
        contentValues.put(AniRssItemsContract.UserFavorites.USER_ID, userFavorites.getUser_id());
        contentValues.put(AniRssItemsContract.UserFavorites.ITEM_ID, userFavorites.getItem_id());
        contentValues.put(AniRssItemsContract.UserFavorites.ITEM_TYPE, userFavorites.getItem_type());
        contentValues.put(AniRssItemsContract.UserFavorites.CREATED_AT, userFavorites.getCreated_at());
        contentValues.put(AniRssItemsContract.UserFavorites.UPDATED_AT, userFavorites.getUpdated_at());
        contentValues.put(AniRssItemsContract.UserFavorites.FAV_RANK, userFavorites.getFav_rank());

        mContext.getContentResolver().insert(AniRssItemsContract.UserFavorites.CONTENT_URI, contentValues);
    }

}
