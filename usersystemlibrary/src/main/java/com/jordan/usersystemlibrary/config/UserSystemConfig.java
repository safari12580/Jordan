package com.jordan.usersystemlibrary.config;

/**
 * Created by icean on 2017/2/2.
 */

public class UserSystemConfig {

    public static final String USER_SYSTEM_DEFAULT_ADDRESS = "http://47.90.55.236:8080/safari_web/";

    public static final String URI_CHECK_ACCOUNT = "v0/user/vip/unique.htm";
    public static final String URI_REGISTER = "v0/user/vip/register.htm";
    public static final String URI_LOGIN = "v0/user/vip/login.htm";
    public static final String URI_GET_PERSONAL_INFO = "v0/user/vip/info.htm";
    public static final String URI_GET_ACCOUNT_INFO = "v0/user/vip/accountInfo.htm";
    public static final String URI_MODIFY_USER_INFO_SINGLE = "v0/user/vip/setSingleInfo.htm";
    public static final String URI_MODIFY_USER_INFO_ALL = "v0/user/vip/setAllInfo.htm";
    public static final String URI_MODIFY_PASSWORD = "v0/user/vip/modifyPassword.htm";
    public static final String URI_FORGET_PASSWORD = "v0/user/vip/forgetPassword.htm";
    public static final String URI_LOGOUT = "v0/user/vip/logout.htm";

    public static final String ACCOUNT_TYPE_USER_NAME = "1";//用户名类型账号
    public static final String ACCOUNT_TYPE_CELL_PHONE = "2";//手机类型账号
    public static final String ACCOUNT_TYPE_EMAIL = "3";//email类型账号

    public static final String LOGING_TYPE_BY_PASSWORD = "1";//使用密码登陆
    public static final String LOGIN_TYPE_BY_CODE = "2";//使用验证码登陆

    public static final String VERIFICATION_TYPE_CODE = "1";//密保校验
    public static final String VERIFICATION_TYPE_MOBILE = "2";//手机校验
    public static final String VERIFICATION_TYPE_EMAIL = "3";//电子邮箱校验

    public static final String USER_INFO_NAME = "name";
    public static final String USER_INFO_NICK = "nick";
    public static final String USER_INFO_GENDER = "gender";
    public static final String USER_INFO_AGE = "age";
    public static final String USER_INFO_BIRTHDAY = "birth";
    public static final String USER_INFO_POSITION = "position";
    public static final String USER_INFO_WEIGHT = "weight";
    public static final String USER_INFO_HEIGHT = "height";
    public static final String USER_INFO_QQ = "qq";
    public static final String USER_INFO_IMG = "img";

    public static final String GENDER_MALE = "1";
    public static final String GENDER_FEMALE = "2";

    public static final class CheckAccountMessageConfig {
        public static final String JSON_REQUEST_KEY_ACCOUNT = "account";
        public static final String JSON_REQUEST_KEY_ACCOUNT_TYPE = "type";
    }

    public static final class RegisterMessageConfig {
        public static final String JSON_REQUEST_KEY_ACCOUNT = "account";
        public static final String JSON_REQUEST_KEY_PASSWORD = "password";
        public static final String JSON_REQUEST_KEY_ACCOUNT_TYPE = "type";
        public static final String JSON_REQUEST_KEY_CODE = "code";
    }

    public static final class LoginMessageConfig {
        public static final String JSON_REQUEST_KEY_ACCOUNT = "account";
        public static final String JSON_REQUEST_KEY_PASSWORD = "password";
        public static final String JSON_REQUEST_KEY_LOGIN_TYPE = "type";
        public static final String JSON_REQUEST_KEY_CODE = "code";

        public static final String JSON_RESPONES_ROOT_INFO = "info";
        public static final String JSON_RESPONES_ROOT_TOKEN = "token";

        public static final String JSON_RESPONES_USER_NAME = "username";
        public static final String JSON_RESPONES_NAME = "name";
        public static final String JSON_RESPONES_NICK = "nick";
        public static final String JSON_RESPONES_GENDER = "gender";
        public static final String JSON_RESPONES_AGE = "age";
        public static final String JSON_RESPONES_BIRTHDAY = "birth";
        public static final String JSON_RESPONES_POSITION = "position";
        public static final String JSON_RESPONES_WEIGHT = "weight";
        public static final String JSON_RESPONES_HEIGHT = "height";
        public static final String JSON_RESPONES_QQ = "qq";
        public static final String JSON_RESPONES_MOBILE = "mobile";
        public static final String JSON_RESPONES_EMAIL = "email";
        public static final String JSON_RESPONES_IMG_URL = "imgUrl";
    }

    public static final class GetPersonalMessageConfig {
        public static final String JSON_REQUEST_KEY_ACCOUNT = "account";

        public static final String JSON_RESPONES_ROOT_INFO = "info";

        public static final String JSON_RESPONES_USER_NAME = "username";
        public static final String JSON_RESPONES_NAME = "name";
        public static final String JSON_RESPONES_NICK = "nick";
        public static final String JSON_RESPONES_GENDER = "gender";
        public static final String JSON_RESPONES_AGE = "age";
        public static final String JSON_RESPONES_BIRTHDAY = "birth";
        public static final String JSON_RESPONES_POSITION = "position";
        public static final String JSON_RESPONES_WEIGHT = "weight";
        public static final String JSON_RESPONES_HEIGHT = "height";
        public static final String JSON_RESPONES_QQ = "qq";
        public static final String JSON_RESPONES_MOBILE = "mobile";
        public static final String JSON_RESPONES_EMAIL = "email";
        public static final String JSON_RESPONES_IMG_URL = "imgUrl";
    }

    public static final class GetAccountMessageConfig {
        public static final String JSON_REQUEST_KEY_ACCOUNT = "account";

        public static final String JSON_RESPONES_VIPID = "vipId";
        public static final String JSON_RESPONES_USER_NAME = "username";
        public static final String JSON_RESPONES_MOBILE = "mobile";
        public static final String JSON_RESPONES_EMAIL = "email";
    }

    public static final class ModifyInfoSingleMessageConfig {
        public static final String JSON_REQUEST_KEY_KEY = "key";
        public static final String JSON_REQUEST_KEY_VALUE = "value";
    }

    public static final class ModifyInfoAllMessageConfig {
        public static final String JSON_REQUEST_NAME = "name";
        public static final String JSON_REQUEST_NICK = "nick";
        public static final String JSON_REQUEST_GENDER = "gender";
        public static final String JSON_REQUEST_AGE = "age";
        public static final String JSON_REQUEST_BIRTHDAY = "birth";
        public static final String JSON_REQUEST_POSITION = "position";
        public static final String JSON_REQUEST_WEIGHT = "weight";
        public static final String JSON_REQUEST_HEIGHT = "height";
        public static final String JSON_REQUEST_QQ = "qq";
        public static final String JSON_REQUEST_IMG_URL = "imgUrl";
    }

    public static final class ModifyPasswordMessageConfig {
        public static final String JSON_REQUEST_OLD_PASSWORD = "oldPass";
        public static final String JSON_REQUEST_NEW_PASSWORD = "newPass";
    }

    public static final class ForgetPasswordMessageConfig {
        public static final String JSON_REQUEST_VIP_ID = "vipId";
        public static final String JSON_REQUEST_PASSWORD = "password";
        public static final String JSON_REQUEST_VERIFICATION_TYPE = "type";
        public static final String JSON_REQUEST_VERIFICATION_CODE = "code";
    }

    public static final class LogoutMessageConfig {

    }
}
