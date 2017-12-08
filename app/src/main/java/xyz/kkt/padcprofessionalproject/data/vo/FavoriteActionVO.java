package xyz.kkt.padcprofessionalproject.data.vo;

import com.google.gson.annotations.SerializedName;

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
}
