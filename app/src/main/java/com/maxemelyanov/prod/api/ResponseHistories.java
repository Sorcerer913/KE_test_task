package com.maxemelyanov.prod.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.maxemelyanov.prod.model.HistoriesData;

public class ResponseHistories {

    @SerializedName("histories")
    @Expose
    private List<HistoriesData> histories = null;

    public List<HistoriesData> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoriesData> histories) {
        this.histories = histories;
    }

}