package com.jordan.project.utils;

import android.app.Activity;
import android.content.Intent;

import com.jordan.project.config.ActivityActionConfig;

/**
 * Created by icean on 2017/1/21.
 */

public final class ActivityUtils {

    public static boolean startLoginActivitySafe(Activity activity, String user_phone, String user_password) {
        Intent start_login_activity_intent = new Intent(ActivityActionConfig.ACTION_TO_LOGIN);
        start_login_activity_intent.putExtra(ActivityActionConfig.KEY_LOGIN_USER_NAME, user_phone);
        start_login_activity_intent.putExtra(ActivityActionConfig.KEY_LOGIN_USER_PASSWORD, user_password);
        try {
            activity.startActivityForResult(start_login_activity_intent, ActivityActionConfig.REQUEST_CODE_LOGIN);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean startRegisterActivitySafe(Activity activity) {
        Intent start_register_activity_intent = new Intent(ActivityActionConfig.ACTION_TO_REGISTER);
        try {
            activity.startActivityForResult(start_register_activity_intent, ActivityActionConfig.REQUEST_CODE_REGISTER);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean startForgetPasswordActivitySafe(Activity activity, String phone_number) {
        Intent start_fp_activity_intent = new Intent(ActivityActionConfig.ACTION_TO_FORGET_PASSWORD);
        start_fp_activity_intent.putExtra(ActivityActionConfig.KEY_LOGIN_USER_NAME, phone_number);
        try {
            activity.startActivityForResult(start_fp_activity_intent, ActivityActionConfig.REQUEST_CODE_FORGET_PASSWORD);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getInputStrInfo(Intent input_intent, String input_key) {
        if (input_intent.hasExtra(input_key)) {
            String input_value = input_intent.getStringExtra(input_key);
            return input_value;
        } else {
            return null;
        }
    }

    public static long getInputLongInfo(Intent input_intent, String input_key) {
        if (input_intent.hasExtra(input_key)) {
            long input_value = input_intent.getLongExtra(input_key, -1L);
            return input_value;
        } else {
            return -1L;
        }
    }
}
