package example.mamak.cryptocurrency20;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
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
    private float mPriceUsd;

    @SerializedName("price_btc")
    @Expose
    private double mPriceBtc;

    @SerializedName("market_cap_usd")
    @Expose
    private String mMarketCapUsd;

    @SerializedName("percent_change_1h")
    @Expose
    private String mPercentChange1h;

    @SerializedName("percent_change_24h")
    @Expose
    private float mPriceChange24h;

    @SerializedName("percent_change_7d")
    @Expose
    private String mPercentChange7d;

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

    public void setRank(int rank) {
        mRank = rank;
    }

    public float getPriceUsd() {
        return mPriceUsd;
    }

    public void setPriceUsd(float priceUsd) {
        mPriceUsd = priceUsd;
    }

    public double getPriceBtc() {
        return mPriceBtc;
    }

    public void setPriceBtc(double priceBtc) {
        mPriceBtc = priceBtc;
    }

    public String getMarketCapUsd() {
        return mMarketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        mMarketCapUsd = marketCapUsd;
    }

    public String getPercentChange1h() {
        return mPercentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        mPercentChange1h = percentChange1h;
    }

    public float getPriceChange24h() {
        return mPriceChange24h;
    }

    public void setPriceChange24h(float priceChange24h) {
        mPriceChange24h = priceChange24h;
    }

    public String getPercentChange7d() {
        return mPercentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        mPercentChange7d = percentChange7d;
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
