package com.jordan.usersystemlibrary.utils;

import com.jordan.usersystemlibrary.data.CheckAccountInfo;
import com.jordan.usersystemlibrary.data.ForgetPasswordInfo;
import com.jordan.usersystemlibrary.data.GetAccountDataInfo;
import com.jordan.usersystemlibrary.data.GetUserDataInfo;
import com.jordan.usersystemlibrary.data.LoginInfo;
import com.jordan.usersystemlibrary.data.ModifyAllUserDataInfo;
import com.jordan.usersystemlibrary.data.ModifyPasswordInfo;
import com.jordan.usersystemlibrary.data.ModifySingleUserDataInfo;
import com.jordan.usersystemlibrary.data.RegisterInfo;

/**
 * Created by icean on 2017/2/2.
 */

public final class UserSystemUtils {

    public static String createCheckAccountMainRequest(String user_account, String account_type) {
        CheckAccountInfo info = new CheckAccountInfo(user_account, account_type);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createRegisterMainRequest(String user_account, String password, String account_type, String code){
        RegisterInfo info = new RegisterInfo(user_account, password, account_type,code);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createLoginMainRequest(String user_account, String login_type, String login_password, String login_code) {
        LoginInfo info = new LoginInfo(user_account, login_password, login_type, login_code);
        String info_str = info.toJsonStr();
        return  info_str;
    }

    public static String createGetPersonalDataMainRequest(String user_account) {
        GetUserDataInfo info = new GetUserDataInfo(user_account);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createGetAccountDataMainRequest(String user_account) {
        GetAccountDataInfo info = new GetAccountDataInfo(user_account);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createModifySingleUserInfoMainRequest(String input_key, String input_value) {
        ModifySingleUserDataInfo info = new ModifySingleUserDataInfo(input_key, input_value);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createModifyAllUserInfoMainRequest(String name, String nick, String gender, String age,
                                                            String birthday, String position, String weight,
                                                            String height, String QQ, String img) {
        ModifyAllUserDataInfo info = new ModifyAllUserDataInfo(name, nick, gender, age, birthday, position, weight, height, QQ, img);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createModifyPasswordInfoMainRequest(String old_password, String new_password) {
        ModifyPasswordInfo info = new ModifyPasswordInfo(old_password, new_password);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createForgetPasswordInfoMainRequest(String vip_id, String user_password, String apply_type, String code) {
        ForgetPasswordInfo info = new ForgetPasswordInfo(vip_id, user_password, apply_type, code);
        String info_str = info.toJsonStr();
        return info_str;
    }

    public static String createLogoutMainRequest() {
        return "";
    }
}
