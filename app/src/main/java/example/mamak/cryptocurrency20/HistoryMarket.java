package example.mamak.cryptocurrency20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryMarket {
    private static final String TAG = "HistoryMarket";

    public List<HistoryItem> getHistoryBySymbol(String symbol) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com/data/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<HistoryData> call = retrofitAPI.
                getHistoryData(symbol, "USD", 30, 1, "CCCAGG");
        List<HistoryItem> list = new ArrayList<>();

        try {
            list = call.execute().body().getHistoryItemList();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

}
