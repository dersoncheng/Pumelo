package com.derson.pumelo.home;

import com.derson.pumelo.location.LocationManager;

/**
 * Created by chengli on 15/8/8.
 */
public class MainInteractorImpl implements MainInteractor{

    @Override
    public void locate(OnLocationListener listener) {
        LocationManager.getInstance().locate(listener);
    }

    @Override
    public void unlocate(OnLocationListener listener) {
        LocationManager.getInstance().unlocate(listener);
    }

    @Override
    public void stoplocate() {
        LocationManager.getInstance().stoplocate();
    }
}
