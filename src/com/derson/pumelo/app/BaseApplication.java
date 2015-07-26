package com.derson.pumelo.app;

import android.app.Application;

/**
 * Created by chengli on 15/7/26.
 */
public class BaseApplication extends Application{

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        if(instance == null){
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
