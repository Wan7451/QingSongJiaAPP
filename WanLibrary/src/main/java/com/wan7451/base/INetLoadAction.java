package com.wan7451.base;

/**
 * Created by Administrator on 2015/10/24.
 */
public interface INetLoadAction {
    void onLoading();

    void onLoadedFinish();

    void onLoadedError();

    void onLoadedNoData();
}
