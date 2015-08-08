package com.derson.pumelo.mvp;

import com.derson.pumelo.network.HttpClient;

/**
 * Created by chengli on 15/8/8.
 */
public abstract class BasePresenter {

    protected String mToken;

    /**
     * 页面销毁时取消所有请求
     */
    public void destroy() {
        HttpClient.getClient().getRequestQueue().cancelAll(this.mToken);
    }
}
