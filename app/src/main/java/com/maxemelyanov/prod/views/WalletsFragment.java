package com.maxemelyanov.prod.views;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxemelyanov.prod.R;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.model.WalletData;
import com.maxemelyanov.prod.presenters.WalletsPresenter;
import com.maxemelyanov.prod.recyclerview.CustomRecyclerViewAdapter;

import java.util.LinkedList;
import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;


public class WalletsFragment extends MvpAppCompatFragment implements IWalletsView {

    @InjectPresenter
    public WalletsPresenter presenter;

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
    public void loading() {
        //TODO
    }

    @Override
    public void update(List<WalletData> data) {
        wallets_list.clear();
        wallets_list.addAll(data);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void success(List<WalletData> data) {
        update(data);
        ((MainActivity) getActivity()).success();

    }

    @Override
    public void error(ErrorType type) {
        ((MainActivity) getActivity()).error(type);
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

        WalletData data = new WalletData();
        data.setWalletName("TestWallet");
        data.setBalance("1,000,000");
        wallets_list.add(data);

        recyclerView.getAdapter().notifyDataSetChanged();

        return rootView;
    }
}