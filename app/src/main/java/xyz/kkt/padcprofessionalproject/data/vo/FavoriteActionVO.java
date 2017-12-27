package xyz.kkt.padcprofessionalproject.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class FavoriteActionVO {

    @SerializedName("favorite-id")
    private String favoriteId;

    @SerializedName("favorite-date")
    private String favoriteDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID, favoriteId);
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE, favoriteDate);
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_USER_ID, actedUser.getUserId());
        contentValues.put(MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID, newsId);


        return contentValues;
    }

    public static FavoriteActionVO parseFromCursor(Cursor cursor) {
        FavoriteActionVO favoriteAction = new FavoriteActionVO();
        favoriteAction.favoriteId = cursor.getString(cursor.getColumnIndex(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID));
        favoriteAction.favoriteDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE));
        favoriteAction.actedUser = ActedUserVO.parseFromCursor(cursor);

        return favoriteAction;
    }
}
