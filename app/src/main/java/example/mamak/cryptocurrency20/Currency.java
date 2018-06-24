package example.mamak.cryptocurrency20;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Currency implements Serializable {
    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("symbol")
    @Expose
    private String mSymbol;

    @SerializedName("rank")
    @Expose
    private int mRank;

    @SerializedName("price_usd")
    @Expose
    private double mPriceUsd;

    @SerializedName("price_btc")
    @Expose
    private double mPriceBtc;

    @SerializedName("market_cap_usd")
    @Expose
    private long mMarketCapUsd;

    @SerializedName("percent_change_1h")
    @Expose
    private float mPercentChange1h;

    @SerializedName("percent_change_24h")
    @Expose
    private float mPriceChange24h;

    @SerializedName("percent_change_7d")
    @Expose
    private float mPercentChange7d;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public int getRank() {
        return mRank;
    }

    public double getPriceUsd() {
        return mPriceUsd;
    }

    public double getPriceBtc() {
        return mPriceBtc;
    }

    public long getMarketCapUsd() {
        return mMarketCapUsd;
    }

    public float getPercentChange1h() {
        return mPercentChange1h;
    }

    public float getPriceChange24h() {
        return mPriceChange24h;
    }

    public float getPercentChange7d() {
        return mPercentChange7d;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "mName='" + mName + '\'' +
                ", mSymbol='" + mSymbol + '\'' +
                ", mRank=" + mRank +
                ", mPriceUsd=" + mPriceUsd +
                ", mPriceBtc=" + mPriceBtc +
                ", mMarketCapUsd='" + mMarketCapUsd + '\'' +
                ", mPercentChange1h='" + mPercentChange1h + '\'' +
                ", mPriceChange24h=" + mPriceChange24h +
                ", mPercentChange7d='" + mPercentChange7d + '\'' +
                '}';
    }
}
