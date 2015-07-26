package com.derson.pumelo.network.volley.toolbox;


import com.derson.pumelo.network.volley.AuthFailureError;
import com.derson.pumelo.network.volley.NetworkResponse;
import com.derson.pumelo.network.volley.ParseError;
import com.derson.pumelo.network.volley.Request;
import com.derson.pumelo.network.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * chengli
 * @param <T> Gson返回格式的网络请求
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    public GsonRequest(int method, int methodId, String url, Class<T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, methodId, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    public GsonRequest(String url, Class<T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(int methodId, T response) {
        listener.onResponse(methodId, response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
