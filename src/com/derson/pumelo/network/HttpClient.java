package com.derson.pumelo.network;

import com.derson.pumelo.app.BaseApplication;
import com.derson.pumelo.network.volley.Request;
import com.derson.pumelo.network.volley.RequestQueue;
import com.derson.pumelo.network.volley.Response;
import com.derson.pumelo.network.volley.toolbox.GsonRequest;
import com.derson.pumelo.network.volley.toolbox.Volley;

/**
 * Created by chengli on 15/7/26.
 * 单例http操作类
 */
public class HttpClient {

    /**
     * 单例对象
     */
    private static HttpClient instance;
    /**
     * http请求队列
     */
    private RequestQueue mRequestQueue;


    public static HttpClient getClient() {
        if (null == instance) {
            synchronized (HttpClient.class) {
                if (null == instance) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    private HttpClient() {
        mRequestQueue = Volley.newRequestQueue(BaseApplication.getInstance());
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    /**
     * 发送http请求
     *
     * @param methodType
     * @param methodId
     * @param listener
     * @param errorListener
     * @param clazz
     * @param <T>
     */
    public <T> void sendRequest(int methodType, int methodId, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> clazz) {
        if (null != mRequestQueue) {
            GsonRequest request = new GsonRequest(methodType, methodId, "", clazz, listener, errorListener);
            mRequestQueue.add(request);
        }
    }
}
