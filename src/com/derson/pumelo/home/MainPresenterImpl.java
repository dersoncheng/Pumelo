package com.derson.pumelo.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.location.BDLocation;
import com.derson.pumelo.location.LocationManager;
import com.derson.pumelo.util.ToastUtil;

/**
 * Created by chengli on 15/8/8.
 */
public class MainPresenterImpl implements MainPresenter,OnLocationListener{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }


    @Override
    public void initLoacation(Context context) {
        mainInteractor.locate(this);
    }

    @Override
    public void quit() {
        mainInteractor.unlocate(this);
    }

    @Override
    public void stopLocation() {
        mainInteractor.stoplocate();
    }

    @Override
    public void onLocateSuccess(BDLocation location) {
        mainView.showMessage("定位成功");
        mainView.removeLoading();
        mainView.displayAddress(location.getAddrStr());
        stopLocation();
    }

    @Override
    public void onLocateFail(int errorType) {
        mainView.removeLoading();
        mainView.showMessage("定位失败，请重试");
        stopLocation();
    }
}
