package com.maxemelyanov.prod.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maxemelyanov.prod.model.HistoriesData;
import com.maxemelyanov.prod.model.WalletData;

import java.util.List;

public class HistoriesViewModel extends ViewModel {
    private MutableLiveData<List<HistoriesData>> histories;

    public LiveData<List<HistoriesData>> getHistories() {
        if (histories == null) {
            histories = new MutableLiveData<List<HistoriesData>>();
        }
        return histories;
    }

    public void setHistories(List<HistoriesData> wallets) {
        this.histories.setValue(wallets);
    }
}
