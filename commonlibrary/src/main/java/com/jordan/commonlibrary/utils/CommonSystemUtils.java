package com.jordan.commonlibrary.utils;

import com.jordan.commonlibrary.config.CommonSystemConfig;
import com.jordan.commonlibrary.data.CheckCodeInfo;
import com.jordan.commonlibrary.data.CheckVersionInfo;
import com.jordan.commonlibrary.data.SendCodeInfo;
import com.jordan.commonlibrary.data.UploadMediaInfo;
import com.jordan.commonlibrary.data.ViewMediasInfo;
import com.jordan.httplibrary.utils.DES3Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by icean on 2017/1/27.
 */

public class CommonSystemUtils {

    public static String createUploadMediaMainRequest(String type, String file_full_path) {
        try {
            UploadMediaInfo current_file_obj = new UploadMediaInfo(type, file_full_path);
            String upload_file_obj = current_file_obj.toJsonStr();
            String result_json_str = DES3Util.encode(upload_file_obj.toString());
            return result_json_str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createUploadMediasMainRequest(ArrayList<UploadMediaInfo> upload_media_files) {
        if (null != upload_media_files && upload_media_files.size() > 0) {
            try {
                JSONArray file_json_array = new JSONArray();
                for (UploadMediaInfo current_file_obj : upload_media_files){
                    file_json_array.put(current_file_obj.toJsonStr());
                }
                JSONObject all_file_json_obj = new JSONObject();
                all_file_json_obj.put(CommonSystemConfig.MediasMessageConfig.JSON_MEDIA_ROOT, file_json_array);
                String all_file_json_str = DES3Util.decode(all_file_json_obj.toString());
                return all_file_json_str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String createViewMediasMainRequest(String[] view_ids) {
        ViewMediasInfo view_medias_info = new ViewMediasInfo(view_ids);
        String view_medias_info_str = view_medias_info.toJsonStr();
        return view_medias_info_str;
    }

    public static String createCheckVersionMainRequest(String device_type) {
        CheckVersionInfo info = new CheckVersionInfo(device_type);
        String check_version_str = info.toJsonStr();
        return  check_version_str;
    }

    public static String createGetCodeMainRequest(String user_account, String apply_type, String genre) {
        SendCodeInfo info = new SendCodeInfo(user_account, apply_type, genre);
        String get_code_str = info.toJsonStr();
        return get_code_str;
    }

    public static String createCheckCodeMainRequest(String user_account, String apply_type, String code) {
        CheckCodeInfo info = new CheckCodeInfo(user_account, apply_type, code);
        String check_code_str = info.toJsonStr();
        return check_code_str;
    }
}
