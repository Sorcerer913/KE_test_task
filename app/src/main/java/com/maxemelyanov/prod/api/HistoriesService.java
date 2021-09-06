package com.maxemelyanov.prod.api;

import static com.maxemelyanov.prod.api.APIConfig.HISTORIES_PART;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HistoriesService {
    @GET(HISTORIES_PART)
    Call<ResponseHistories> getHistoriesData();
}