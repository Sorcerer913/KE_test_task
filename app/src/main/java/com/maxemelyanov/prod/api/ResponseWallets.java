package com.maxemelyanov.prod.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.maxemelyanov.prod.model.WalletData;

public class ResponseWallets {

    @SerializedName("wallets")
    @Expose
    private List<WalletData> wallets = null;

    public List<WalletData> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletData> wallets) {
        this.wallets = wallets;
    }

}

