package com.jordan.project.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.jordan.project.data.OfflineUserData;

/**
 * Created by icean on 2017/1/20.
 */

public final class OfflineUserConfig {

    private static final String OFFLINE_USER_DATA_FILE = "offline_userdata";

    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_TOKEN = "user_token";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_NICK = "user_nick";
    private static final String KEY_LAST_LOGIN_TIME = "last_login_time";

    public static final OfflineUserData getCurrentUserData(Context ctx) {
        SharedPreferences offline_user_data_file = ctx.getSharedPreferences(OFFLINE_USER_DATA_FILE, Context.MODE_PRIVATE);
        String user_token = offline_user_data_file.getString(KEY_USER_TOKEN, "");
        if (TextUtils.isEmpty(user_token)) {
            return null;
        } else {
            String user_name = offline_user_data_file.getString(KEY_USER_NAME, "");
            String user_phone = offline_user_data_file.getString(KEY_USER_PHONE, "");
            String user_email = offline_user_data_file.getString(KEY_USER_EMAIL, "");
            String user_nick = offline_user_data_file.getString(KEY_USER_NICK, "");
            long last_login_datetime = offline_user_data_file.getLong(KEY_LAST_LOGIN_TIME, -1L);
            OfflineUserData current_user_data = new OfflineUserData(user_name, user_token, user_phone, user_email, user_nick, last_login_datetime);
            return current_user_data;
        }
    }

    public static final void saveUserData(Context ctx, String user_name, String user_token, String user_phone,
                                          String user_email, String user_nick, long last_login_datetime) {
        SharedPreferences.Editor file_editor = ctx.getSharedPreferences(OFFLINE_USER_DATA_FILE, Context.MODE_PRIVATE).edit();
        file_editor.clear();
        file_editor.commit();
        file_editor.putString(KEY_USER_NAME, user_name);
        file_editor.putString(KEY_USER_TOKEN, user_token);
        file_editor.putString(KEY_USER_PHONE, user_phone);
        file_editor.putString(KEY_USER_EMAIL, user_email);
        file_editor.putString(KEY_USER_NICK, user_nick);
        file_editor.putLong(KEY_LAST_LOGIN_TIME, last_login_datetime);
    }
}
