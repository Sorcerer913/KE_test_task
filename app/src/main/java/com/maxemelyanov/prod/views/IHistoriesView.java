package com.maxemelyanov.prod.views;

import com.maxemelyanov.prod.api.ErrorType;
import com.maxemelyanov.prod.model.HistoriesData;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IHistoriesView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void loading();

    @StateStrategyType(SingleStateStrategy.class)
    void update(List<HistoriesData> data);

    @StateStrategyType(SingleStateStrategy.class)
    void success(List<HistoriesData> data);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void error(ErrorType type);

}
