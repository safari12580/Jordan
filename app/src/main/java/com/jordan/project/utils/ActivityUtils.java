package com.jordan.project.utils;

import android.app.Activity;
import android.content.Intent;

import com.jordan.project.config.ActivityActionConfig;

/**
 * Created by icean on 2017/1/21.
 */

public final class ActivityUtils {

    public static boolean startLoginActivitySafe(Activity ctx, String user_name, String user_password) {
        Intent start_login_activity_intent = new Intent(ActivityActionConfig.ACTION_TO_LOGIN);
        start_login_activity_intent.putExtra(ActivityActionConfig.KEY_LOGIN_USER_NAME, user_name);
        start_login_activity_intent.putExtra(ActivityActionConfig.KEY_LOGIN_USER_PASSWORD, user_password);
        try {
            ctx.startActivityForResult(start_login_activity_intent, ActivityActionConfig.REQUEST_CODE_LOGIN);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
