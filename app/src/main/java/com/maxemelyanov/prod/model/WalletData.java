package com.maxemelyanov.prod.model;

import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletData extends Data{

    @SerializedName("wallet_name")
    @Expose
    private String walletName;
    @SerializedName("balance")
    @Expose
    private String balance;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Pair<String, String> getData(){
        return new Pair<>(walletName, balance);
    }

}
