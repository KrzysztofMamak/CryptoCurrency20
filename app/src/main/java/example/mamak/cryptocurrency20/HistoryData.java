package example.mamak.cryptocurrency20;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryData {
    @SerializedName("Data")
    @Expose
    private List<HistoryItem> mHistoryItemList;

    public List<HistoryItem> getHistoryItemList() {
        return mHistoryItemList;
    }

    public void setHistoryItemList(List<HistoryItem> historyItemList) {
        mHistoryItemList = historyItemList;
    }
}
