package com.maxemelyanov.prod.api;

import static com.maxemelyanov.prod.api.APIConfig.WALLETS_PART;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WalletsService {
    @GET(WALLETS_PART)
    Call<ResponseWallets> getWalletsData();
}