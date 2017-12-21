package xyz.kkt.padcprofessionalproject.network.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;

/**
 * Created by Lenovo on 12/16/2017.
 */

public class MMNewsContract {

    public static final String CONTENT_AUTHORITY = SFCNewsApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_NEWS = "news";
    public static final String PATH_IMAGES_IN_NEWS = "images_in_news";
    public static final String PATH_PUBLICATION = "publication";
    public static final String PATH_FAVORITE_ACTION = "favorite_action";
    public static final String PATH_COMMENT = "comment";
    public static final String PATH_SENT_TO = "sent_to";
    public static final String PATH_ACTED_USER = "acted_user";

    public static class NewsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEWS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;//for retriving a single row from the table


        public static final String TABLE_NAME = PATH_NEWS;
        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_BRIEF = "brief";
        public static final String COLUMN_DETAILS = "details";
        public static final String COLUMN_POSTED_DATE = "posted_date";
        public static final String COLUMN_PUBLICATION_ID = "publication_id";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class ImagesInNewsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_IMAGES_IN_NEWS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_IMAGES_IN_NEWS;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_IMAGES_IN_NEWS;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_IMAGES_IN_NEWS;

        public static final String COLUMN_IMAGES_NEWS_ID = "images_in_news";
        public static final String COLUMN_IMAGE_URL = "images";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class PublicationEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PUBLICATION).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PUBLICATION;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PUBLICATION;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_PUBLICATION;

        public static final String COLUMN_PUBLICATION_ID = "publication_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_LOGO = "logo";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class FavoriteActionEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_ACTION).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE_ACTION;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE_ACTION;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_FAVORITE_ACTION;

        public static final String COLUMN_FAVORITE_ID = "favorite_id";
        public static final String COLUMN_FAVORITE_DATE = "favorite_date";
        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_USER_ID = "user_id";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class CommentEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENT).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_COMMENT;

        public static final String COLUMN_COMMENT_ID = "comment_id";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_COMMENT_DATE = "comment_date";
        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_USER_ID = "user_id";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class SentToEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SENT_TO).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SENT_TO;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SENT_TO;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_SENT_TO;

        public static final String COLUMN_SENT_TO_ID = "sent_to_id";
        public static final String COLUMN_SENT_DATE = "sent_date";
        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_SENDER_ID = "sender_id";
        public static final String COLUMN_RECEIVER_ID = "receiver_id";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class ActedUserEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ACTED_USER).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACTED_USER;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACTED_USER;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_ACTED_USER;

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_PROFILE_IMAGE = "profile_image";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
