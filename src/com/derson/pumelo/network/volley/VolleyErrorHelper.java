package com.derson.pumelo.network.volley;

import android.content.Context;

import com.derson.pumelo.R;
import com.derson.pumelo.app.BaseApplication;

/**
 * Created by chengli on 15/7/5.
 * Volley错误信息处理类
 */
public class VolleyErrorHelper {

    //错误类型列表
    //AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。
    //NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。
    //NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。
    //ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
    //ServerError：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
    //TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy。

    public static String getErrorMessage(Object error) {
        Context cxt = BaseApplication.getInstance();
        if(error instanceof TimeoutError) {
            return cxt.getString(R.string.timeout_error);
        } else if(isNetworkProblem(error)) {
            return cxt.getString(R.string.network_common_error);
        } else if(isServerProblem(error)) {
            return cxt.getString(R.string.server_error);
        } else if(error instanceof ParseError) {
            return cxt.getString(R.string.data_parser_error);
        }
        return cxt.getString(R.string.network_common_error);
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError)
                || (error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError)
                || (error instanceof AuthFailureError);
    }
}
