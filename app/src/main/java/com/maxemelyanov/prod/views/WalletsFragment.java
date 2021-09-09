package com.maxemelyanov.prod.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxemelyanov.prod.R;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.model.WalletData;
import com.maxemelyanov.prod.presenters.WalletsPresenter;
import com.maxemelyanov.prod.recyclerview.CustomRecyclerViewAdapter;
import com.maxemelyanov.prod.viewmodel.WalletsViewModel;

import java.util.LinkedList;
import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;


public class WalletsFragment extends MvpAppCompatFragment implements IWalletsView {

    public static final String TAG = "WalletsFragment";

    @InjectPresenter
    public WalletsPresenter presenter;

    WalletsViewModel view_model;


    private final LinkedList<WalletData> wallets_list = new LinkedList<>();

    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter<WalletData> recycler_view_adapter;

    public WalletsFragment() {
        // Required empty public constructor
    }

    public static WalletsFragment newInstance() {
        return new WalletsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_wallets, container, false);

        recyclerView = rootView.findViewById(R.id.wallets_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view_adapter = new CustomRecyclerViewAdapter<>(getContext(), wallets_list);
        recyclerView.setAdapter(recycler_view_adapter);

//        WalletData data = new WalletData();
//        data.setWalletName("TestWallet");
//        data.setBalance("1,000,000");
//        wallets_list.add(data);

        view_model = new ViewModelProvider(getActivity()).get(/*"WalletsViewModel",*/ WalletsViewModel.class);
        view_model.getWallets().observe(getViewLifecycleOwner(), this::updateUI);

        updateUI();

//        presenter.loadData();

        return rootView;
    }

    @Override
    public void loading() {
        //TODO
    }

    private void updateUI(){
        List<WalletData> wallets = view_model.getWallets().getValue();
        if (wallets != null) updateUI(wallets);
        else Log.d(TAG, "updateUI: wallets == null");
    }
    private void updateUI(@NonNull List<WalletData> wallets){
        wallets_list.clear();
        wallets_list.addAll(wallets);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void update(List<WalletData> data) {
        Log.d(TAG, "update: ");
        view_model.setWallets(data);
    }

    @Override
    public void success(List<WalletData> data) {
        Log.d(TAG, "success: ");
        update(data);
        ((MainActivity) getActivity()).success();

    }

    @Override
    public void error(ErrorType type) {
        updateUI();
        ((MainActivity) getActivity()).error(type);
    }

}