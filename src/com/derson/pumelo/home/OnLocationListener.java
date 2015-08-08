package com.derson.pumelo.home;

import com.baidu.location.BDLocation;

/**
 * Created by chengli on 15/8/8.
 */
public interface OnLocationListener {

    public void onLocateSuccess(BDLocation location);

    public void onLocateFail(int errorType);
}
