package com.maxemelyanov.prod.views;

import android.os.Bundle;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.maxemelyanov.prod.R;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.presenters.MainPresenter;

import java.io.File;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    public MainPresenter presenter;

    public static final String TAG = "MainActivity";

//    TextView textView;
    SwipeRefreshLayout refresh_layout;

    private WalletsFragment wallets_fragment;
    private HistoriesFragment history_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) { replaceFragment }

        // Creating fragments
        wallets_fragment = WalletsFragment.newInstance();
        history_fragment = HistoriesFragment.newInstance();

        // Setting fragments
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_wallets, wallets_fragment)
                .commitNow();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_histories, history_fragment)
                .commitNow();

        refresh_layout = findViewById(R.id.refresh_layout);

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadData();
            }
        });

    }

    int states_end_counter = 0;

    @Override
    public void loading() {
        states_end_counter = 0;
        refresh_layout.setRefreshing(true);
        wallets_fragment.presenter.loadData();
        history_fragment.presenter.loadData();
    }

    @Override
    public void success() {
        states_end_counter += 1;
        if (states_end_counter == 2) {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void error(ErrorType type) {   //TODO для каждого отдельно
        states_end_counter += 1;

        int error_msg_str_id;

        if (type == ErrorType.NO_INTERNET_CONNECTION){
            Log.d(TAG, "Error: NO_INTERNET_CONNECTION");
            error_msg_str_id = R.string.no_internet_error;
        } else if (type == ErrorType.PURE_CONNECTION){
            Log.d(TAG, "Error: PURE_CONNECTION");
            error_msg_str_id = R.string.pure_internet_connection_error;
        } else if (type == ErrorType.HTTP_429){
            Log.d(TAG, "Error: HTTP_429");
            error_msg_str_id = R.string.too_many_requests_error;
        } else if (type == ErrorType.HTTP_500){
            Log.d(TAG, "Error: HTTP_500");
            error_msg_str_id = R.string.server_error;
        } else{
            Log.d(TAG, "Error: null");
            error_msg_str_id = R.string.other_error;
        }

        Snackbar.make(refresh_layout, error_msg_str_id, Snackbar.LENGTH_LONG).show();
        if (states_end_counter == 2) {
            refresh_layout.setRefreshing(false);
        }
    }


}