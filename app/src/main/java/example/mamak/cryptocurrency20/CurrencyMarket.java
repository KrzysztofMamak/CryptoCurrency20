package example.mamak.cryptocurrency20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyMarket {
    private static final String TAG = "CurrencyMarket";

    public List<Currency> getCurrenciesByPosition(int start) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<Currency>> call = retrofitAPI.getCurrencies(start, 50);
        List<Currency> list = new ArrayList<>();

        try {
            list = call.execute().body();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }
}
