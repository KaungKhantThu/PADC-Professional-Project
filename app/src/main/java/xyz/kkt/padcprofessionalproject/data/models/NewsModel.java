package xyz.kkt.padcprofessionalproject.data.models;


import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.data.vo.CommentVO;
import xyz.kkt.padcprofessionalproject.data.vo.FavoriteActionVO;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.data.vo.PublicationVO;
import xyz.kkt.padcprofessionalproject.data.vo.SendToVO;
import xyz.kkt.padcprofessionalproject.events.RestApiEvents;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgent;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgentImpl;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsProvider;
import xyz.kkt.padcprofessionalproject.utils.AppConstants;
import xyz.kkt.padcprofessionalproject.utils.ConfigUtils;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class NewsModel {

    //private List<NewsVO> mNews;

    private static final String MM_NEWS = "mm_news";

    @Inject
    MMNewsDataAgent mDataAgent;

    @Inject
    ConfigUtils mUonfigUtils;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public NewsModel(Context context) {
        EventBus.getDefault().register(this);
        //mNews =new ArrayList<>()

        SFCNewsApp sfcNewsApp = (SFCNewsApp) context.getApplicationContext();
        sfcNewsApp.getSFCAppComponent().inject(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

    }

    public void startLoadingMMNews(final Context context) {
//        mDataAgent.loadMMNews(AppConstants.ACCESS_TOKEN,
//                mUonfigUtils.loadPageIndex(), context);


        DatabaseReference mmNewsDBR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mmNewsNodeDBR = mmNewsDBR.child(MM_NEWS);
        mmNewsNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<NewsVO> newsList = new ArrayList<>();
                for (DataSnapshot newsDSS : dataSnapshot.getChildren()) {
                    NewsVO news = newsDSS.getValue(NewsVO.class);
                    newsList.add(news);
                }

                Log.d(SFCNewsApp.LOG_TAG, "newsList size : " + newsList.size());
                saveNewsData(context, newsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    public List<NewsVO> getNews() {
//        return mNews;
//    }

    public void loadMoreNews(Context context) {
//        int pageIndex = mUonfigUtils.loadPageIndex();
//        mDataAgent.loadMMNews(AppConstants.ACCESS_TOKEN,
//                pageIndex, context);
    }

    public void forceRefreshNews(Context context) {
//        mNews = new ArrayList<>();
//        mUonfigUtils.savePageIndex(1);
//        startLoadingMMNews(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
//        mNews.addAll(event.getLoadNews());
//        mUonfigUtils.savePageIndex(event.getLoadedPageIndex() + 1);

        mUonfigUtils.savePageIndex(event.getLoadedPageIndex() + 1);
        saveNewsData(event.getContext(), event.getLoadNews());
    }

    private void saveNewsData(Context context, List<NewsVO> newsList) {


        //TODO Logic to save the data in Persistence Layer

        ContentValues[] newsCVs = new ContentValues[newsList.size()];
        List<ContentValues> publicationCVList = new ArrayList<>();
        List<ContentValues> imagesInNewsCVList = new ArrayList<>();
        List<ContentValues> favoriteActionCVList = new ArrayList<>();
        List<ContentValues> commentCVList = new ArrayList<>();
        List<ContentValues> sentToCVList = new ArrayList<>();
        List<ContentValues> usersInActionCVList = new ArrayList<>();

        for (int index = 0; index < newsCVs.length; index++) {
            NewsVO news = newsList.get(index);
            newsCVs[index] = news.parseToContentValues();

            PublicationVO publication = news.getPublication();
            publicationCVList.add(publication.parseToContentValues());

            for (String imageUrl : news.getImages()) {
                ContentValues imagesInNewsCV = new ContentValues();
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGES_NEWS_ID, news.getNewsId());
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL, imageUrl);
                imagesInNewsCVList.add(imagesInNewsCV);
            }

            for (FavoriteActionVO favoriteAction : news.getFavoriteActions()) {
                ContentValues favoriteActionCV = favoriteAction.parseToContentValues(news.getNewsId());
                favoriteActionCVList.add(favoriteActionCV);

                ContentValues usersInActionCV = favoriteAction.getActedUser().parseToContentValues();
                usersInActionCVList.add(usersInActionCV);
            }

            for (CommentVO comment : news.getCommentActions()) {
                ContentValues commentCV = comment.parseToContentValues(news.getNewsId());
                commentCVList.add(commentCV);

                ContentValues usersInActionCV = comment.getActedUser().parseToContentValues();
                usersInActionCVList.add(usersInActionCV);
            }

            for (SendToVO sentTo : news.getSentToActions()) {
                ContentValues sentToCV = sentTo.parseToContentValues(news.getNewsId());
                sentToCVList.add(sentToCV);

                ContentValues senderCV = sentTo.getSender().parseToContentValues();
                usersInActionCVList.add(senderCV);

                ContentValues receiverCV = sentTo.getReceiver().parseToContentValues();
                usersInActionCVList.add(receiverCV);
            }


        }


        int insertedRows = context.getContentResolver().bulkInsert(MMNewsContract.NewsEntry.CONTENT_URI,
                newsCVs);
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedRows);

        int insertedPublications = context.getContentResolver().bulkInsert(MMNewsContract.PublicationEntry.CONTENT_URI,
                publicationCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedPublications);

        int insertedImagesInNews = context.getContentResolver().bulkInsert(MMNewsContract.ImagesInNewsEntry.CONTENT_URI,
                imagesInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedImagesInNews);

        int insertedFavoriteAction = context.getContentResolver().bulkInsert(MMNewsContract.FavoriteActionEntry.CONTENT_URI,
                favoriteActionCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedFavoriteAction);

        int insertedComment = context.getContentResolver().bulkInsert(MMNewsContract.CommentEntry.CONTENT_URI,
                commentCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedComment);

        int insertedSentTo = context.getContentResolver().bulkInsert(MMNewsContract.SentToEntry.CONTENT_URI,
                sentToCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedSentTo);

        int insertedUsersInAction = context.getContentResolver().bulkInsert(MMNewsContract.ActedUserEntry.CONTENT_URI,
                usersInActionCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedUsersInAction);

    }

    public void authenticateUserWithGoogleAccount(final GoogleSignInAccount signInAccount,
                                                  final UserAuthenticateDelegate delegate) {
        Log.d(SFCNewsApp.LOG_TAG, "signInAccount Id :" + signInAccount.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(SFCNewsApp.LOG_TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(SFCNewsApp.LOG_TAG, "signInWithCredential", task.getException());
                            delegate.onFailureAuthenticate(task.getException().getMessage());
                        } else {
                            Log.d(SFCNewsApp.LOG_TAG, "signInWithCredential - successful");
                            mFirebaseUser = mFirebaseAuth.getCurrentUser();
                            delegate.onSuccessAuthenticate(signInAccount);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(SFCNewsApp.LOG_TAG, "OnFailureListener : " + e.getMessage());
                        delegate.onFailureAuthenticate(e.getMessage());
                    }
                });
    }

    public void uploadFile(String fileToUpload, final UploadFileCallback uploadFileCallback) {
        Uri file = Uri.parse(fileToUpload);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference pathToUpload = storage.getReferenceFromUrl("gs://mmnews-9a029.appspot.com/user_news_imgs");

        StorageReference uploadingFile = pathToUpload.child(file.getLastPathSegment());
        UploadTask uploadTask = uploadingFile.putFile(file);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                uploadFileCallback.onUploadFailed(e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uploadedImageUrl = taskSnapshot.getDownloadUrl();
                Log.d(SFCNewsApp.LOG_TAG, "Uploaded Image Url : " + uploadedImageUrl);
                uploadFileCallback.onUploadSucceeded(uploadedImageUrl.toString());
            }
        });

    }

    public boolean isUserAuthenticate() {
        return mFirebaseUser != null;
    }

    public void publishNews(String photoPath, final String newsContent) {
        uploadFile(photoPath, new UploadFileCallback() {
            @Override
            public void onUploadSucceeded(String uploadedPaths) {
                List<String> images = new ArrayList<>();
                images.add(uploadedPaths);

                NewsVO newsToPublish = new NewsVO(newsContent, newsContent, images, new Date().toString());

                newsToPublish.setPublication(PublicationVO.dummyPublication());
                DatabaseReference mmNewsDBR = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mmNewsNodeDBR = mmNewsDBR.child(MM_NEWS);
                mmNewsNodeDBR.child(newsToPublish.getPostedDate()).setValue(newsToPublish);

            }

            @Override
            public void onUploadFailed(String msg) {

            }
        });
    }

    public interface UserAuthenticateDelegate {
        void onSuccessAuthenticate(GoogleSignInAccount account);

        void onFailureAuthenticate(String errorMsg);
    }

    public interface UploadFileCallback {
        void onUploadSucceeded(String uploadedPaths);

        void onUploadFailed(String msg);
    }

}
