/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.derson.pumelo.network.volley.toolbox;


import android.util.Log;


import com.derson.pumelo.network.volley.NetworkResponse;
import com.derson.pumelo.network.volley.ParseError;
import com.derson.pumelo.network.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * A request for retrieving a {@link org.json.JSONArray} response body at a given URL.
 */
public class JsonArrayRequest extends JsonRequest<String> {

    /**
     * Creates a new request.
     * @param method the HTTP method to use
     * @param url URL to fetch the JSON from
     * @param requestBody A {@link String} to post with the request. Null is allowed and
     *   indicates no parameters will be posted along with request.
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public JsonArrayRequest(int method, int methodId, String url, String requestBody,
                            Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, methodId,url, requestBody, listener,
                errorListener);
    }

    /**
     * Creates a new request.
     * @param url URL to fetch the JSON from
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public JsonArrayRequest(int methodId,String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, methodId,url, null, listener, errorListener);
    }

    /**
     * Creates a new request.
     * @param method the HTTP method to use
     * @param url URL to fetch the JSON from
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public JsonArrayRequest(int method, int methodId,String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, methodId,url, null, listener, errorListener);
    }

    /**
     * Creates a new request.
     * @param method the HTTP method to use
     * @param url URL to fetch the JSON from
     * @param jsonRequest A {@link org.json.JSONArray} to post with the request. Null is allowed and
     *   indicates no parameters will be posted along with request.
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public JsonArrayRequest(int method, int methodId, String url, JSONArray jsonRequest,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, methodId, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
    }

    /**
     * Creates a new request.
     * @param method the HTTP method to use
     * @param url URL to fetch the JSON from
     * @param jsonRequest A {@link org.json.JSONObject} to post with the request. Null is allowed and
     *   indicates no parameters will be posted along with request.
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public JsonArrayRequest(int method,int methodId, String url, JSONObject jsonRequest,
                            Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, methodId,url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
    }

    /**
     * Constructor which defaults to <code>GET</code> if <code>jsonRequest</code> is
     * <code>null</code>, <code>POST</code> otherwise.
     *
     * @see #JsonArrayRequest(int, String, org.json.JSONArray, com.derson.pumelo.network.volley.Response.Listener, com.derson.pumelo.network.volley.Response.ErrorListener)
     */
    public JsonArrayRequest(int methodId, String url, JSONArray jsonRequest, Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, methodId, url, jsonRequest,
                listener, errorListener);
    }

    /**
     * Constructor which defaults to <code>GET</code> if <code>jsonRequest</code> is
     * <code>null</code>, <code>POST</code> otherwise.
     *
     * @see #JsonArrayRequest(int, String, org.json.JSONObject, com.derson.pumelo.network.volley.Response.Listener, com.derson.pumelo.network.volley.Response.ErrorListener)
     */
    public JsonArrayRequest(int methodId, String url, JSONObject jsonRequest, Response.Listener<String> listener,
                             Response.ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, methodId,url, jsonRequest,
                listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d("JsonArrayRequest", jsonString);
//            return Response.success(new JSONArray(jsonString),
//                    HttpHeaderParser.parseCacheHeaders(response));
            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
//        catch (JSONException je) {
//            return Response.error(new ParseError(je));
//        }
    }
}
