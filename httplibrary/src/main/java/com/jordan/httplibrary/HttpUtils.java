package com.jordan.httplibrary;


import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jordan.httplibrary.utils.Base64;
import com.jordan.httplibrary.utils.CommonUtils;
import com.jordan.httplibrary.utils.HttpUtilsConfig;
import com.jordan.httplibrary.utils.data.ResponseData;
import com.safari.core.protocol.RequestMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icean on 2017/1/26.
 */

public final class HttpUtils {

    public static String sendHttpRequest(String target_address, RequestMessage.Request request_message){
        try {
            JSONObject all_data = new JSONObject();
            String data_str = Base64.encode(request_message.toByteArray());

            all_data.put(HttpUtilsConfig.KEY_ROOT_DATA, data_str);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(HttpUtilsConfig.KEY_ROOT, all_data.toString()));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HttpUtilsConfig.DEFAULT_CHART);
            HttpPost post = new HttpPost(target_address);
            post.setEntity(entity);
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HttpUtilsConfig.OVER_TIME);
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpUtilsConfig.HTTP_SUCCCESS){
                HttpEntity result_entity = response.getEntity();
                String response_string = EntityUtils.toString(result_entity);
                return response_string;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
