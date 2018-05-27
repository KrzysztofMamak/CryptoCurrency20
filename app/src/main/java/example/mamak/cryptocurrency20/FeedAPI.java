package example.mamak.cryptocurrency20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface FeedAPI {

    @Headers("Content-type: application/json")
    @GET("v1/ticker/?limit=100")
    Call<List<Currency>> getFeed();
}
