package com.derson.pumelo.home;

/**
 * Created by chengli on 15/8/6.
 */
public interface MainView {

    public void showLoading();

    public void removeLoading();

    public void showMessage(String info);

    public void displayAddress(String address);
}
