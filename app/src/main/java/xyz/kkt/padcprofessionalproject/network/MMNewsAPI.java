package xyz.kkt.padcprofessionalproject.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.kkt.padcprofessionalproject.network.response.GetNewsResponse;

/**
 * Created by Lenovo on 12/3/2017.
 */

public interface MMNewsAPI {

    @FormUrlEncoded
    @POST("v1/getMMNews.php")
    Call<GetNewsResponse> loadMMNews(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

}
