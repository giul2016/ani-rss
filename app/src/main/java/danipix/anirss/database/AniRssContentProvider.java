package danipix.anirss.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import danipix.anirss.constants.AniRssItemsContract;

/**
 * Created by Dani Pix on 2/7/2015.
 */
public class AniRssContentProvider extends ContentProvider {
    private static final int USER_INFORMATION_LIST = 1;
    private static final int USER_INFORMATION_ID = 2;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(AniRssItemsContract.AUTHORITY,
                AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE,
                USER_INFORMATION_LIST);
        URI_MATCHER.addURI(AniRssItemsContract.AUTHORITY,
                AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE + "/#",
                USER_INFORMATION_ID);
    }

    private DatabaseHelper databaseHelper;

    @Override
    public boolean onCreate() {
        databaseHelper = DatabaseHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (URI_MATCHER.match(uri)) {
            case USER_INFORMATION_LIST:
                builder.setTables(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE);
                // No sort order is necessary here, there will be only one row in this table because there's only one currently logged in user.
                break;
            case USER_INFORMATION_ID:
                builder.setTables(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE);
                // Limit query to one row at most.
                builder.appendWhere(AniRssItemsContract.UserInformation.USER_ID + " = " +
                        uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor cursor = null;
        try {
            cursor = builder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        database.close();
        cursor.close();
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case USER_INFORMATION_LIST:
                return AniRssItemsContract.UserInformation.CONTENT_TYPE;
            case USER_INFORMATION_ID:
                return AniRssItemsContract.UserInformation.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI from type: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (URI_MATCHER.match(uri) != USER_INFORMATION_LIST) {
            throw new IllegalArgumentException("Unsupported URI for insertion into database: " + uri);
        }
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        long id;
        switch (URI_MATCHER.match(uri)) {
            case USER_INFORMATION_LIST:
                id = database.insert(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, null, values);
                database.close();
                return getUriForId(id, uri);

            default:
                database.close();
                return null;

        }


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int deleteCount;
        String idStr;
        String where;
        switch (URI_MATCHER.match(uri)) {
            case USER_INFORMATION_LIST:
                deleteCount = database.delete(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, selection, selectionArgs);
                database.close();
                break;
            case USER_INFORMATION_ID:
                idStr = uri.getLastPathSegment();
                where = AniRssItemsContract.UserInformation.USER_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                deleteCount = database.delete(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, where, selectionArgs);
                database.close();
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI at deletion: " + uri);
        }
        if (deleteCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int updateCount;
        String idStr;
        String where;

        switch (URI_MATCHER.match(uri)) {
            case USER_INFORMATION_LIST:
                updateCount = database.update(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, values, selection, selectionArgs);
                database.close();
                break;
            case USER_INFORMATION_ID:
                idStr = uri.getLastPathSegment();
                where = AniRssItemsContract.UserInformation.USER_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = database.update(AniRssItemsContract.UserInformation.USER_INFORMATION_TABLE, values, where, selectionArgs);
                database.close();
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI at update: " + uri);
        }
        if (updateCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateCount;
    }


    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            getContext().getContentResolver().notifyChange(itemUri, null);
            return itemUri;
        }
        return null;
    }
}