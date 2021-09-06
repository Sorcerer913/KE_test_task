package com.maxemelyanov.prod.presenters;

import android.util.Log;

import com.maxemelyanov.prod.api.APIServerConstructor;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.api.ResponseWallets;
import com.maxemelyanov.prod.api.WalletsService;
import com.maxemelyanov.prod.model.WalletData;
import com.maxemelyanov.prod.views.IMainView;
import com.maxemelyanov.prod.views.IWalletsView;
import com.maxemelyanov.prod.views.MainActivity;

import java.io.IOException;
import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class WalletsPresenter extends MvpPresenter<IWalletsView> {

    WalletsService service;

    public static final String TAG = "WalletsPresenter";

    List<WalletData> wallets = null;

    public WalletsPresenter(){

        service = APIServerConstructor.CreateService(WalletsService.class);
        loadData();
    }

    public void loadData(){

        getViewState().loading();

//        if (wallets != null) getViewState().update(wallets);

        Call<ResponseWallets> call = service.getWalletsData();
        call.enqueue(new Callback<ResponseWallets>() {
            @Override
            public void onResponse(Call<ResponseWallets> call, Response<ResponseWallets> response) {
                if (response.body() != null){
                    wallets = response.body().getWallets();
                    getViewState().success(wallets);
                } else {
                    Log.d(TAG, "onResponse: null response.body()");
                    getViewState().error(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseWallets> call, Throwable t) {    //TODO: CHECK
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (t instanceof IOException) getViewState().error(ErrorType.NO_INTERNET_CONNECTION);
                else if (t instanceof RuntimeException) getViewState().error(ErrorType.HTTP_500);
                else getViewState().error(null);
            }

        });
    }

}