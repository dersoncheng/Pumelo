package com.derson.pumelo.home;

import com.derson.pumelo.mvp.BaseView;

/**
 * Created by chengli on 15/8/6.
 */
public interface MainView extends BaseView{

    public void showMessage(String info);

    public void displayAddress(String address);
}
