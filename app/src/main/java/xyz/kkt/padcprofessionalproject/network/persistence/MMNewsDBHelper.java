package xyz.kkt.padcprofessionalproject.network.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 12/16/2017.
 */

public class MMNewsDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "mmNews.db";
    public static final int DB_VERSION = 3;

    public static final String SQL_CREATE_NEWS = "CREATE TABLE " + MMNewsContract.NewsEntry.TABLE_NAME + " (" +
            MMNewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.NewsEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.NewsEntry.COLUMN_BRIEF + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_POSTED_DATE + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_DETAILS + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.NewsEntry.COLUMN_NEWS_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_IMAGES_IN_NEWS = "CREATE TABLE " + MMNewsContract.ImagesInNewsEntry.TABLE_NAME + " (" +
            MMNewsContract.ImagesInNewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGES_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL + " TEXT, " +

            " UNIQUE (" + MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_PUBLICATION = "CREATE TABLE " + MMNewsContract.PublicationEntry.TABLE_NAME + " (" +
            MMNewsContract.PublicationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID + " VARCHAR(256), " +
            MMNewsContract.PublicationEntry.COLUMN_TITLE + " TEXT, " +
            MMNewsContract.PublicationEntry.COLUMN_LOGO + " TEXT, " +

            " UNIQUE (" + MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_FAVORITE_ACTION = "CREATE TABLE " + MMNewsContract.FavoriteActionEntry.TABLE_NAME + " (" +
            MMNewsContract.FavoriteActionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID + " VARCHAR(256), " +
            MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE + " TEXT, " +
            MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.FavoriteActionEntry.COLUMN_USER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_COMMENT = "CREATE TABLE " + MMNewsContract.CommentEntry.TABLE_NAME + " (" +
            MMNewsContract.CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.CommentEntry.COLUMN_COMMENT_ID + " VARCHAR(256), " +
            MMNewsContract.CommentEntry.COLUMN_COMMENT + " TEXT, " +
            MMNewsContract.CommentEntry.COLUMN_COMMENT_DATE + " TEXT, " +
            MMNewsContract.CommentEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.CommentEntry.COLUMN_USER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.CommentEntry.COLUMN_COMMENT_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_SEND_TO = "CREATE TABLE " + MMNewsContract.SentToEntry.TABLE_NAME + " (" +
            MMNewsContract.SentToEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.SentToEntry.COLUMN_SENT_TO_ID + " VARCHAR(256), " +
            MMNewsContract.SentToEntry.COLUMN_SENT_DATE + " TEXT, " +
            MMNewsContract.SentToEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.SentToEntry.COLUMN_SENDER_ID + " VARCHAR(256), " +
            MMNewsContract.SentToEntry.COLUMN_RECEIVER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.SentToEntry.COLUMN_SENT_TO_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_ACTED_USER = "CREATE TABLE " + MMNewsContract.ActedUserEntry.TABLE_NAME + " (" +
            MMNewsContract.ActedUserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.ActedUserEntry.COLUMN_USER_ID + " VARCHAR(256), " +
            MMNewsContract.ActedUserEntry.COLUMN_USER_NAME + " TEXT, " +
            MMNewsContract.ActedUserEntry.COLUMN_PROFILE_IMAGE + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.ActedUserEntry.COLUMN_USER_ID + ") ON CONFLICT REPLACE" +
            ");";

    public MMNewsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACTED_USER);
        db.execSQL(SQL_CREATE_PUBLICATION);
        db.execSQL(SQL_CREATE_NEWS);
        db.execSQL(SQL_CREATE_IMAGES_IN_NEWS);

        db.execSQL(SQL_CREATE_FAVORITE_ACTION);
        db.execSQL(SQL_CREATE_COMMENT);
        db.execSQL(SQL_CREATE_SEND_TO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.SentToEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.CommentEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.FavoriteActionEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.ImagesInNewsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.NewsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.PublicationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.ActedUserEntry.TABLE_NAME);

        onCreate(db);
    }
}
