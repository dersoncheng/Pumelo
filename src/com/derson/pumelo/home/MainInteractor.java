package com.derson.pumelo.home;

import com.derson.pumelo.location.LocationManager;
import com.derson.pumelo.mvp.BaseInteractor;

/**
 * Created by chengli on 15/8/8.
 */
public class MainInteractor implements BaseInteractor{

    @Override
    public void init() {

    }

    public void locate(OnLocationListener listener) {
        LocationManager.getInstance().locate(listener);
    }

    public void unlocate(OnLocationListener listener) {
        LocationManager.getInstance().unlocate(listener);
    }

    public void stoplocate() {
        LocationManager.getInstance().stoplocate();
    }
}
