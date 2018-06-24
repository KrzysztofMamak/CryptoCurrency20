package example.mamak.cryptocurrency20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @Headers("Content-type: application/json")
    @GET("v1/ticker/?limit=100")
    Call<List<Currency>> getFeed();

    @Headers("Content-type: application/json")
    @GET("v1/ticker/")
    Call<List<Currency>> getCurrencies(@Query("start") int start,
                                       @Query("limit") int limit);

    @Headers("Content-type: application/json")
    @GET("histoday?")
    Call<HistoryData> getHistoryData(@Query("fsym") String fsym,
                                     @Query("tsym") String tsym,
                                     @Query("limit") int limit,
                                     @Query("aggregate") int aggregate,
                                     @Query("e") String e);
}
