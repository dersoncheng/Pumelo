package com.derson.pumelo.location;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.derson.pumelo.app.BaseApplication;

/**
 * Created by chengli on 15/7/27.
 * 位置定位管理类
 */
public class LocationManager {


    public static final String LOCATION_SUCCESS = "location_success";
    public static final String LOCATION_FAIL = "location_fail";
    public static final String LOCATION_DATA = "location_data";
    /**
     * 单例对象
     */
    private static LocationManager locationManager;

    private LocationClient locationClient;

    private BDLocation location;

    private BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            switch (bdLocation.getLocType()) {
                case BDLocation.TypeNetWorkLocation:
                    location = bdLocation;
                    Intent localIntent = new Intent();
                    localIntent.setAction(LOCATION_SUCCESS);
                    localIntent.putExtra(LOCATION_DATA, location);
                    LocalBroadcastManager.getInstance(BaseApplication.getInstance()).sendBroadcast(localIntent);
                    locationClient.stop();
                    break;

                case BDLocation.TypeOffLineLocationNetworkFail:
                case BDLocation.TypeOffLineLocationFail:
                    Intent localIntent2 = new Intent();
                    localIntent2.setAction(LOCATION_FAIL);
                    LocalBroadcastManager.getInstance(BaseApplication.getInstance()).sendBroadcast(localIntent2);
                    locationClient.stop();
                    break;

            }
        }
    };

    private LocationManager() {

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
//        option.setOpenGps(true);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(locationListener);
    }

    public void locate() {
        if(null != locationClient) {
            locationClient.start();
        }
    }


}
