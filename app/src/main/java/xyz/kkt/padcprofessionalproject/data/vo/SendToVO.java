package xyz.kkt.padcprofessionalproject.data.vo;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class SendToVO {

    @SerializedName("sent-to-id")
    private String sendToId;

    @SerializedName("sent-date")
    private String sendDate;

    @SerializedName("acted-user")
    private ActedUserVO sender;

    @SerializedName("received-user")
    private ActedUserVO receiver;

    public String getSendToId() {
        return sendToId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public ActedUserVO getSender() {
        return sender;
    }

    public ActedUserVO getReceiver() {
        return receiver;
    }

    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MMNewsContract.SentToEntry.COLUMN_SENT_TO_ID, sendToId);
        contentValues.put(MMNewsContract.SentToEntry.COLUMN_SENT_DATE, sendDate);
        contentValues.put(MMNewsContract.SentToEntry.COLUMN_SENDER_ID, sender.getUserId());
        contentValues.put(MMNewsContract.SentToEntry.COLUMN_RECEIVER_ID, receiver.getUserId());
        contentValues.put(MMNewsContract.SentToEntry.COLUMN_NEWS_ID, newsId);


        return contentValues;
    }

}
