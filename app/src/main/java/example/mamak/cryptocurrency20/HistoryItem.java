package example.mamak.cryptocurrency20;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryItem {
    @SerializedName("time")
    @Expose
    private long mTime;
    @SerializedName("close")
    @Expose
    private double mClose;
    @SerializedName("high")
    @Expose
    private double mHigh;
    @SerializedName("low")
    @Expose
    private double mLow;
    @SerializedName("open")
    @Expose
    private double mOpen;
    @SerializedName("volumefrom")
    @Expose
    private double mVolumeFrom;
    @SerializedName("volumeto")
    @Expose
    private double mVolumeTo;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public double getClose() {
        return mClose;
    }

    public void setClose(double close) {
        mClose = close;
    }

    public double getHigh() {
        return mHigh;
    }

    public void setHigh(double high) {
        mHigh = high;
    }

    public double getLow() {
        return mLow;
    }

    public void setLow(double low) {
        mLow = low;
    }

    public double getOpen() {
        return mOpen;
    }

    public void setOpen(double open) {
        mOpen = open;
    }

    public double getVolumeFrom() {
        return mVolumeFrom;
    }

    public void setVolumeFrom(double volumeFrom) {
        mVolumeFrom = volumeFrom;
    }

    public double getVolumeTo() {
        return mVolumeTo;
    }

    public void setVolumeTo(double volumeTo) {
        mVolumeTo = volumeTo;
    }
}
