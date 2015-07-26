package com.derson.pumelo.network.volley.toolbox;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by chengli on 15/7/11.
 */
public class HttpUtils {

    /**
     * 拼接Get请求参数
     *
     * @param params
     * @param encoding
     * @return
     * @throws com.derson.pumelo.network.volley.AuthFailureError
     */
    public static String getGetParamsUrl(HttpRequestParams params, String encoding) {
        HttpRequestParams postParams = params;
        if (postParams != null && postParams.urlParams.size() > 0) {
            return encodegetParameters(postParams.urlParams, encoding);
        }
        return null;
    }

    private static String encodegetParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
}
