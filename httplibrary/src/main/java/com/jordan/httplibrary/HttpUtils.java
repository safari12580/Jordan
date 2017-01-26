package com.jordan.httplibrary;

import android.text.TextUtils;

import com.jordan.httplibrary.utils.Base64;
import com.jordan.httplibrary.utils.HttpUtilsConfig;
import com.jordan.httplibrary.utils.data.ResponeData;
import com.safari.core.protocol.RequestMessage;
import com.safari.core.protocol.ResponseMessage;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icean on 2017/1/26.
 */

public final class HttpUtils {

    public String sendHttpRequest(String target_address, RequestMessage.Request request_message){
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
                String respone_string = EntityUtils.toString(result_entity);
                return respone_string;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponeData isSuccess(String respone_data){
        try {
            JSONObject respone_object = new JSONObject(respone_data);
            String result = respone_object.getString(HttpUtilsConfig.KEY_RESP_ROOT_RESULT);
            if (HttpUtilsConfig.RESULT_CODE_SUCCESS.equals(result)){
                return null;
            } else {
                JSONObject false_data_obj = new JSONObject(respone_object.getJSONObject(HttpUtilsConfig.KEY_RESP_ROOT_DATA).toString());
                ResponeData data = new ResponeData(false_data_obj.getString(HttpUtilsConfig.KEY_RESP_CODE),
                        false_data_obj.getString(HttpUtilsConfig.KEY_RESP_MESSAGE));
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
