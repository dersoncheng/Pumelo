package com.derson.pumelo.home;

import com.derson.pumelo.location.LocationManager;

/**
 * Created by chengli on 15/8/8.
 */
public class MainInteractorImpl implements MainInteractor{

    @Override
    public void locate() {
        LocationManager.getInstance().locate();
    }
}
