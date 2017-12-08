package xyz.kkt.padcprofessionalproject.data.vo;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.padcprofessionalproject.data.models.NewsModel;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class CommentVO {

    @SerializedName("comment-id")
    private String commentId;

    @SerializedName("comment")
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}
