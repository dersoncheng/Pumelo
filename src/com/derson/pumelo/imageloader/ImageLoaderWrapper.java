package com.derson.pumelo.imageloader;

import android.graphics.drawable.DrawableContainer;
import android.net.Uri;

import com.derson.pumelo.app.BaseApplication;
import com.derson.pumelo.imageloader.fresco.ImageCacheErrorLogger;
import com.derson.pumelo.imageloader.fresco.ImageDiskConfig;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
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
        //磁盘缓存配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder().setCacheErrorLogger(new ImageCacheErrorLogger()).setBaseDirectoryName(ImageDiskConfig.BASE_CACHE_DIRECTORY).setMaxCacheSize(ImageDiskConfig.MAX_DISK_CACHE_SIZE).setMaxCacheSizeOnLowDiskSpace(ImageDiskConfig.MAX_DISK_CACHE_SIZE_ON_LOWSPACE).setMaxCacheSizeOnVeryLowDiskSpace(ImageDiskConfig.MAX_DISK_CACHE_SIZE_ON_VERYLOWSPACE).build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(BaseApplication.getInstance()).setProgressiveJpegConfig(new SimpleProgressiveJpegConfig()).setMainDiskCacheConfig(diskCacheConfig).build();
        Fresco.initialize(BaseApplication.getInstance(), config);
    }


    /**
     * 加载图片
     *
     * @param view 图片控件
     * @param url  图片url
     */
    public static void loadImage(SimpleDraweeView view, String url) {
        loadImage(view, url, false, null, false);
    }

    /**
     * 加载图片
     *
     * @param view       图片控件
     * @param url        图片url
     * @param allowRetry 失败后是否允许点击重试加载（4次）
     */
    public static void loadImage(SimpleDraweeView view, String url, boolean allowRetry) {
        loadImage(view, url, allowRetry, null, false);
    }

    /**
     * 加载图片
     *
     * @param view     图片控件
     * @param url      图片url
     * @param listener 图片加载监听
     */
    public static void loadImage(SimpleDraweeView view, String url, ControllerListener listener) {
        loadImage(view, url, false, listener, false);
    }

    /**
     * 加载图片
     *
     * @param view       图片控件
     * @param url        图片url
     * @param allowRetry 失败后是否允许点击重试加载
     * @param listener   图片加载监听
     */
    public static void loadImage(SimpleDraweeView view, String url, boolean allowRetry, ControllerListener listener) {
        loadImage(view, url, allowRetry, listener, false);
    }

    /**
     * 加载图片
     *
     * @param view             图片控件
     * @param url              图片url
     * @param allowRetry       失败后是否允许点击重试加载
     * @param listener         图片加载监听
     * @param allowProgressive 是否允许图片加载渐进式显示（目前仅支持jpeg格式）
     */
    public static void loadImage(SimpleDraweeView view, String url, boolean allowRetry, ControllerListener listener, boolean allowProgressive) {
        loadImage(view, url, allowRetry, listener, allowProgressive, false);
    }

    /**
     * 加载图片
     *
     * @param view             图片控件
     * @param url              图片url
     * @param allowRetry       失败后是否允许点击重试加载
     * @param listener         图片加载监听
     * @param allowProgressive 是否允许图片加载渐进式显示（目前仅支持jpeg格式）
     * @param isAutoPlay       是否允许加载完图片后自动播放（仅支持webP/GIF格式）
     */
    public static void loadImage(SimpleDraweeView view, String url, boolean allowRetry, ControllerListener listener, boolean allowProgressive, boolean isAutoPlay) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).setProgressiveRenderingEnabled(allowProgressive).build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(imageRequest).setOldController(view.getController()).setTapToRetryEnabled(allowRetry).setAutoPlayAnimations(isAutoPlay).setControllerListener(listener).build();
        view.setController(draweeController);
    }
}
