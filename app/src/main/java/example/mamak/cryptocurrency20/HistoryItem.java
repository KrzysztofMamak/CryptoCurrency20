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

    public double getHigh() {
        return mHigh;
    }
}
