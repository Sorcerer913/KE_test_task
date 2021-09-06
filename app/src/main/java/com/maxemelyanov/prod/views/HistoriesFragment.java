package com.maxemelyanov.prod.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maxemelyanov.prod.R;
import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.model.HistoriesData;
import com.maxemelyanov.prod.presenters.HistoriesPresenter;
import com.maxemelyanov.prod.recyclerview.CustomRecyclerViewAdapter;

import java.util.LinkedList;
import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HistoriesFragment extends MvpAppCompatFragment implements IHistoriesView {

    @InjectPresenter
    public HistoriesPresenter presenter;

    private final LinkedList<HistoriesData> histories_list = new LinkedList<>();

    private RecyclerView recyclerView;

    private CustomRecyclerViewAdapter<HistoriesData> recycler_view_adapter;

    public HistoriesFragment() {
        // Required empty public constructor
    }

    public static HistoriesFragment newInstance() {
        return new HistoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_histories, container, false);

        recyclerView = rootView.findViewById(R.id.histories_recycler_view);
        recycler_view_adapter = new CustomRecyclerViewAdapter<>(getContext(), histories_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycler_view_adapter);

        HistoriesData data = new HistoriesData();
        data.setAmount("T");
        data.setCurrency("T");
        data.setBalance("1,000,000");
        data.setEntry("T");
        data.setSender("T");
        data.setRecipient("T");
        histories_list.add(data);

        recyclerView.getAdapter().notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void loading() {
        //TODO
    }

    @Override
    public void update(List<HistoriesData> data) {
        histories_list.clear();
        histories_list.addAll(data);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void success(List<HistoriesData> data) { //TODO
        update(data);
        ((MainActivity) getActivity()).success();
    }

    @Override
    public void error(ErrorType type) {
        ((MainActivity) getActivity()).error(type);
    }
}