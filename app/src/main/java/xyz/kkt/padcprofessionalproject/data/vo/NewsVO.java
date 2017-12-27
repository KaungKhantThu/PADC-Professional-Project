package xyz.kkt.padcprofessionalproject.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;

/**
 * Created by Lenovo on 12/2/2017.
 */

public class NewsVO {

    @SerializedName("news-id")
    private String newsId;

    @SerializedName("brief")
    private String brief;

    @SerializedName("details")
    private String details;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVO publication;

    @SerializedName("favorites")
    private List<FavoriteActionVO> favoriteActions;

    @SerializedName("comments")
    private List<CommentVO> comments;

    @SerializedName("sent-tos")
    private List<SendToVO> sendTos;


    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteActionVO> getFavoriteActions() {
        return favoriteActions;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public List<SendToVO> getSendTos() {
        return sendTos;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MMNewsContract.NewsEntry.COLUMN_NEWS_ID, newsId);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_BRIEF, brief);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_DETAILS, details);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE, postedDate);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID, publication.getPublicationId());

        return contentValues;
    }

    public static NewsVO parseFromCursor(Context context, Cursor cursor) {
        NewsVO news = new NewsVO();
        news.newsId = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_NEWS_ID));
        news.brief = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_BRIEF));
        news.details = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_DETAILS));
        news.postedDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE));

        news.publication = PublicationVO.parseFromCursor(cursor);
        news.images = loadImagesInNews(context, news.newsId);
        news.favoriteActions = loadFavoriteActionInNews(context, news.newsId);

        return news;
    }

    private static List<String> loadImagesInNews(Context context, String newsId) {
        Cursor imagesInNewsCursor = context.getContentResolver().query(MMNewsContract.ImagesInNewsEntry.CONTENT_URI,
                null,
                MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGES_NEWS_ID + " = ?", new String[]{newsId},
                null);

        if (imagesInNewsCursor != null && imagesInNewsCursor.moveToFirst()) {
            List<String> imagesInNews = new ArrayList<>();
            do {
                imagesInNews.add(
                        imagesInNewsCursor.getString((imagesInNewsCursor.getColumnIndex(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL)))
                );
            } while (imagesInNewsCursor.moveToNext());
            imagesInNewsCursor.close();
            return imagesInNews;
        }
        return null;
    }

    private static List<FavoriteActionVO> loadFavoriteActionInNews(Context context, String newsId) {
        Cursor favoriteInNewsCursor = context.getContentResolver().query(MMNewsContract.FavoriteActionEntry.CONTENT_URI, null,
                MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " = ?", new String[]{newsId},
                null);

        if (favoriteInNewsCursor != null && favoriteInNewsCursor.moveToFirst()) {
            List<FavoriteActionVO> favoritesInNews = new ArrayList<>();
            do {
                favoritesInNews.add(
                        FavoriteActionVO.parseFromCursor(favoriteInNewsCursor));
            } while (favoriteInNewsCursor.moveToNext());
            favoriteInNewsCursor.close();
            return favoritesInNews;
        }
        return null;
    }
}
