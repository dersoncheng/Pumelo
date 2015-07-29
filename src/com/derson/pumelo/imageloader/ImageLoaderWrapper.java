package com.derson.pumelo.imageloader;

import android.net.Uri;

import com.derson.pumelo.app.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by chengli on 15/7/26.
 * 图片加载器
 */
public class ImageLoaderWrapper {

    /**
     * 初始化图片加载器
     */
    public static void initLoader() {
        Fresco.initialize(BaseApplication.getInstance());
    }

    /**
     * 加载图片
     *
     * @param view
     * @param url
     */
    public static void loadImage(SimpleDraweeView view, String url) {
        view.setImageURI(Uri.parse(url));
    }
}
