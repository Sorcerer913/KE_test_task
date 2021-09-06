package com.maxemelyanov.prod.presenters;

import com.maxemelyanov.prod.views.HistoriesFragment;
import com.maxemelyanov.prod.views.IMainView;
import com.maxemelyanov.prod.views.WalletsFragment;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public MainPresenter(){
        loadData();
    }

    public void loadData(){
        getViewState().loading();
    }

}
