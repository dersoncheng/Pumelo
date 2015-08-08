package com.derson.pumelo.location;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.derson.pumelo.app.BaseApplication;
import com.derson.pumelo.home.OnLocationListener;

import java.util.ArrayList;

/**
 * Created by chengli on 15/7/27.
 * 位置定位管理类
 */
public class LocationManager {

    /**
     * 单例对象
     */
    private static LocationManager locationManager;

    private LocationClient locationClient;

    private ArrayList<OnLocationListener> listeners;

    private BDLocation location;

    private BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            switch (bdLocation.getLocType()) {
                case BDLocation.TypeNetWorkLocation:
                    location = bdLocation;
                    if(null != listeners) {
                        for(OnLocationListener listener : listeners) {
                            listener.onLocateSuccess(location);
                        }
                    }
                    break;

                case BDLocation.TypeOffLineLocationNetworkFail:
                case BDLocation.TypeOffLineLocationFail:
                    if(null != listeners) {
                        for(OnLocationListener listener : listeners) {
                            listener.onLocateFail(bdLocation.getLocType());
                        }
                    }
                    break;

            }
        }
    };

    private LocationManager() {
        listeners = new ArrayList<OnLocationListener>();
    }

    public static LocationManager getInstance() {
        if (null == locationManager) {
            synchronized (LocationManager.class) {
                if (null == locationManager) {
                    locationManager = new LocationManager();
                }
            }
        }
        return locationManager;
    }

    /**
     * 初始化定位
     */
    public void init() {
        locationClient = new LocationClient(BaseApplication.getInstance());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setScanSpan(0);
        option.setOpenGps(false);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(locationListener);
    }

    public void locate(OnLocationListener listener) {
        listeners.add(listener);
        if(null != locationClient) {
            locationClient.start();
        }
    }

    public void unlocate(OnLocationListener listener){
        if(null != listeners) {
            listeners.remove(listener);
        }
    }

    public void stoplocate(){
        if(null != locationClient) {
            locationClient.stop();
        }
    }

}
