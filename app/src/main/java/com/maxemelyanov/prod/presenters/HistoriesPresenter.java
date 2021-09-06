package com.maxemelyanov.prod.presenters;

import android.util.Log;

import com.maxemelyanov.prod.api.APIServerConstructor;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.api.HistoriesService;
import com.maxemelyanov.prod.api.ResponseHistories;
import com.maxemelyanov.prod.api.ResponseWallets;
import com.maxemelyanov.prod.api.WalletsService;
import com.maxemelyanov.prod.model.HistoriesData;
import com.maxemelyanov.prod.model.WalletData;
import com.maxemelyanov.prod.views.IHistoriesView;
import com.maxemelyanov.prod.views.IMainView;

import java.io.IOException;
import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class HistoriesPresenter extends MvpPresenter<IHistoriesView> {

    HistoriesService service;

    public static final String TAG = "HistoriesPresenter";

    List<HistoriesData> histories = null;

    public HistoriesPresenter(){
        service = APIServerConstructor.CreateService(HistoriesService.class);
        loadData();
    }

    public void loadData(){

        getViewState().loading();

//        if (histories != null) getViewState().update(histories);

        Call<ResponseHistories> call = service.getHistoriesData();
        call.enqueue(new Callback<ResponseHistories>() {
            @Override
            public void onResponse(Call<ResponseHistories> call, Response<ResponseHistories> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        histories = response.body().getHistories();
                        getViewState().success(histories);
                    } else {
                        Log.d(TAG, "onResponse: null response.body()");
                        getViewState().error(null);
                    }
                }else if (response.code() == 429){
                    Log.d(TAG, "onResponse: HTTP response code 429");
                    getViewState().error(ErrorType.HTTP_429);
                }else if (response.code() == 500){
                    Log.d(TAG, "onResponse: HTTP response code 500");
                    getViewState().error(ErrorType.HTTP_500);
                }
            }

            @Override
            public void onFailure(Call<ResponseHistories> call, Throwable t) {    //TODO: CHECK
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (t instanceof IOException) getViewState().error(ErrorType.NO_INTERNET_CONNECTION);
                else if (t instanceof RuntimeException) getViewState().error(ErrorType.HTTP_500);
                else getViewState().error(null);
            }
        });
    }

}