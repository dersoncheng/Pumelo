package com.derson.pumelo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by chengli on 15/7/26.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化图片加载器
        Fresco.initialize(this);
    }
}
