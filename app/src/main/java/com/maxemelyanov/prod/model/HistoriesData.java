package com.maxemelyanov.prod.model;

import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoriesData extends Data{

    @SerializedName("entry")
    @Expose
    private String entry;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("recipient")
    @Expose
    private String recipient;
    @SerializedName("balance")
    @Expose
    private String balance;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Pair<String, String> getData(){

        Pair<String,String> pair;

        if (entry.equals("incoming")) {
            pair = new Pair<>(
                    String.format("%s have received payment from %s", recipient, sender),
                    String.format("%s %s", amount != null? amount: balance, currency)
            );
        } else {
            pair = new Pair<>(
                    String.format("%s have cashed out to %s", sender, recipient),
                    String.format("-%s %s", amount != null? amount: balance, currency)
            );
        }

        return pair;
    }

}
