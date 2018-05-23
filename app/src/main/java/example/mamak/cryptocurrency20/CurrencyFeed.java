package example.mamak.cryptocurrency20;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyFeed {

    private static final String TAG = "CurrencyFeed";
    private static final String BASE_URL = "https://api.coinmarketcap.com/";

    private static CurrencyFeed sCurrencyFeed;
    private List<Currency> mCurrencies;

    public static CurrencyFeed get(Context context){
        if (sCurrencyFeed == null) {
            sCurrencyFeed = new CurrencyFeed(context);
        }
        return sCurrencyFeed;
    }

    private CurrencyFeed(Context context) {

        mCurrencies = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FeedAPI feedAPI = retrofit.create(FeedAPI.class);
        final Call<List<Currency>> call = feedAPI.getFeed();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mCurrencies = call.execute().body();
                    Log.d(TAG, mCurrencies.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, String.valueOf(mCurrencies.size()));
    }

    public List<Currency> getCurrencies() {
        return mCurrencies;
    }

    public Currency getCurrency(int rank) {
        for (Currency currency : mCurrencies) {
            if (currency.getRank() == rank) {
                return currency;
            }
        }
        return null;
    }
}
