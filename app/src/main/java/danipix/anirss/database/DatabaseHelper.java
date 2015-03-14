package danipix.anirss.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import danipix.anirss.constants.AniRssItemsContract;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AniRss";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper mInstance = null;

    private static final String DB_USER_INFORMATION_CREATE = "create table "
            + AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE + " ("
            + AniRssItemsContract.UserInformation.ID + " autoincrement integer not null,"
            + AniRssItemsContract.UserInformation.USER_ID + " integer,"
            + AniRssItemsContract.UserInformation.NAME + " text,"
            + AniRssItemsContract.UserInformation.WAIFU + " text,"
            + AniRssItemsContract.UserInformation.WAIFU_OR_HUSBANDO + " text,"
            + AniRssItemsContract.UserInformation.WAIFU_SLUG + " text,"
            + AniRssItemsContract.UserInformation.WAIFU_CHAR_ID + " text,"
            + AniRssItemsContract.UserInformation.LOCATION + " text,"
            + AniRssItemsContract.UserInformation.WEBSITE + " text,"
            + AniRssItemsContract.UserInformation.AVATAR + " text,"
            + AniRssItemsContract.UserInformation.COVER_IMAGE + " text,"
            + AniRssItemsContract.UserInformation.ABOUT + " text,"
            + AniRssItemsContract.UserInformation.BIO + " text,"
            + AniRssItemsContract.UserInformation.KARMA + " integer,"
            + AniRssItemsContract.UserInformation.LIFE_SPENT_ON_ANIME + " integer,"
            + AniRssItemsContract.UserInformation.SHOW_ADULT_CONTENT + " integer,"
            + AniRssItemsContract.UserInformation.TITLE_LANGUAGE_PREFERENCE + " text,"
            + AniRssItemsContract.UserInformation.LAST_LIBRARY_UPDATE + " text,"
            + AniRssItemsContract.UserInformation.FOLLOWING + " integer,"
            + AniRssItemsContract.UserInformation.FAVORITES + " text" + ");";


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_USER_INFORMATION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all data.");

        db.execSQL("DROP TABLE IF EXISTS " + AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE);
        onCreate(db);
    }

    public void emptyTables() {
        SQLiteDatabase database = this.getWritableDatabase();
        if (database != null) {
            database.delete(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, null, null);
        }
    }
}
