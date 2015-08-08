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
public class MainPresenterImpl implements MainPresenter{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(null != intent) {
                if(intent.getAction().equals(LocationManager.LOCATION_SUCCESS)) {
                    BDLocation location = (BDLocation)intent.getParcelableExtra(LocationManager.LOCATION_DATA);
                    mainView.showMessage("定位成功");
                    mainView.removeLoading();
                    mainView.displayAddress(location.getAddrStr());
                } else {
                    mainView.removeLoading();
                    mainView.showMessage("定位失败，请重试");
                }
            }
        }
    };

    @Override
    public void initLoacation(Context context) {
        IntentFilter locationFilter = new IntentFilter();
        locationFilter.addAction(LocationManager.LOCATION_SUCCESS);
        locationFilter.addAction(LocationManager.LOCATION_FAIL);
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, locationFilter);
        mainInteractor.locate();
    }
}
