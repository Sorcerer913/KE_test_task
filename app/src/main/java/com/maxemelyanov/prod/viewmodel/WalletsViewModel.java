package com.maxemelyanov.prod.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maxemelyanov.prod.model.WalletData;

import java.util.List;

public class WalletsViewModel extends ViewModel {
    private MutableLiveData<List<WalletData>> wallets;

    public LiveData<List<WalletData>> getWallets() {
        if (wallets == null) {
            wallets = new MutableLiveData<List<WalletData>>();
        }
        return wallets;
    }

    public void setWallets(List<WalletData> wallets) {
        this.wallets.setValue(wallets);
    }


}
