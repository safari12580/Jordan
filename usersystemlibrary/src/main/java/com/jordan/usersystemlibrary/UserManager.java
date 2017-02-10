package com.jordan.usersystemlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;

import com.jordan.usersystemlibrary.config.UserSystemConfig;
import com.jordan.usersystemlibrary.task.CheckAccountTask;
import com.jordan.usersystemlibrary.task.ForgetPasswordTask;
import com.jordan.usersystemlibrary.task.GetAccountDataTask;
import com.jordan.usersystemlibrary.task.GetUserDataTask;
import com.jordan.usersystemlibrary.task.LoginTask;
import com.jordan.usersystemlibrary.task.LogoutTask;
import com.jordan.usersystemlibrary.task.ModifyAllUserDataTask;
import com.jordan.usersystemlibrary.task.ModifyPasswordTask;
import com.jordan.usersystemlibrary.task.ModifySingleUserDataTask;
import com.jordan.usersystemlibrary.task.RegisterTask;


/**
 * Created by icean on 2017/2/2.
 */

public final class UserManager {
    private Context mContext;
    private Handler mMainThreadHandler;
    private String mRemoteAddress;
    private boolean mIsGranted;
    private String mUserToken;

    private CheckAccountTask mCheckAccountTask;
    private ForgetPasswordTask mForgetPasswordTask;
    private GetAccountDataTask mGetAccountDataTask;
    private GetUserDataTask mGetUserDataTask;
    private LoginTask mLoginTask;
    private LogoutTask mLogoutTask;
    private ModifyAllUserDataTask mModifyAllUserDataTask;
    private ModifyPasswordTask mModifyPasswordTask;
    private ModifySingleUserDataTask mModifySingleUserDataTask;
    private RegisterTask mRegisterTask;

    public UserManager(Context context, Handler main_thread_handler, String user_system_address) {
        mContext = context;
        mMainThreadHandler = main_thread_handler;
        mRemoteAddress = user_system_address;
        mIsGranted = false;
        mUserToken = "";
    }

    public UserManager(Context context, Handler main_thread_handler) {
        this(context, main_thread_handler, UserSystemConfig.USER_SYSTEM_DEFAULT_ADDRESS);
    }

    public void setIsGranted(boolean is_granted) {
        mIsGranted = is_granted;
    }

    public void setUserToken(String user_token){
        mUserToken = user_token;
    }

    public void checkAccount(String user_account, String account_type) {
        if (null != mCheckAccountTask) {
            mCheckAccountTask.cancel(true);
            mCheckAccountTask = null;
        }

        mCheckAccountTask = new CheckAccountTask(mContext, mRemoteAddress, mMainThreadHandler, user_account, account_type, mUserToken, mIsGranted);
        mCheckAccountTask.execute("Begin");
    }

    public void forgetPassword(String vip_id, String password, String verification_type, String code) {
        if (null != mForgetPasswordTask) {
            mForgetPasswordTask.cancel(true);
            mForgetPasswordTask = null;
        }

        mForgetPasswordTask = new ForgetPasswordTask(mContext, mRemoteAddress, mMainThreadHandler, vip_id, password, verification_type, code, mUserToken, mIsGranted);
        mForgetPasswordTask.execute("Begin");
    }

    public void getAccountData(String user_account) {
        if (null != mGetAccountDataTask) {
            mGetAccountDataTask.cancel(true);
            mGetAccountDataTask = null;
        }

        mGetAccountDataTask = new GetAccountDataTask(mContext, mRemoteAddress, mMainThreadHandler, user_account, mUserToken, mIsGranted);
        mGetAccountDataTask.execute("Begin");
    }

    public void getUserData(String user_account) {
        if (null != mGetUserDataTask) {
            mGetUserDataTask.cancel(true);
            mGetUserDataTask = null;
        }

        mGetUserDataTask = new GetUserDataTask(mContext, mRemoteAddress, mMainThreadHandler, user_account, mUserToken, mIsGranted);
        mGetUserDataTask.execute("Begin");
    }

    public void login(String user_account, String password, String login_type, String code){
        if (null != mLoginTask) {
            mLoginTask.cancel(true);
            mLoginTask = null;
        }

        mLoginTask = new LoginTask(mContext, mRemoteAddress, mMainThreadHandler, user_account, login_type, password, code, mUserToken, mIsGranted);
        mLoginTask.execute("Begin");
    }

    public void modifyAllUserData(String name, String nick, String gender, String age,
                                  String birthday, String position, String weight, String height, String QQ, String img) {
        if (null != mModifyAllUserDataTask) {
            mModifyAllUserDataTask.cancel(true);
            mModifyAllUserDataTask = null;
        }

        mModifyAllUserDataTask = new ModifyAllUserDataTask(mContext, mRemoteAddress, mMainThreadHandler,
                name, nick, gender, age, birthday, position, weight, height, QQ, img, mUserToken, mIsGranted);
        mModifyAllUserDataTask.execute("Begin");
    }

    public void modifyPassword(String old_password, String new_password){
        if (null != mModifyPasswordTask) {
            mModifyPasswordTask.cancel(true);
            mModifyPasswordTask = null;
        }

        mModifyPasswordTask = new ModifyPasswordTask(mContext, mRemoteAddress, mMainThreadHandler, old_password, new_password, mUserToken, mIsGranted);
        mModifyPasswordTask.execute("Begin");
    }

    public void modifySingleUserData(String input_key, String input_value) {
        if (null != mModifySingleUserDataTask) {
            mModifySingleUserDataTask.cancel(true);
            mModifySingleUserDataTask = null;
        }

        mModifySingleUserDataTask = new ModifySingleUserDataTask(mContext, mRemoteAddress, mMainThreadHandler, input_key, input_value, mUserToken, mIsGranted);
        mModifySingleUserDataTask.execute("Begin");
    }

    public void logout(){
        if (null != mLogoutTask) {
            mLogoutTask.cancel(true);
            mLogoutTask = null;
        }

        mLogoutTask = new LogoutTask(mContext, mRemoteAddress, mMainThreadHandler, mUserToken, mIsGranted);
        mLogoutTask.execute("Begin");
    }

    public void saveUserData(String user_token, String user_json) {
        mUserToken = user_token;
        SharedPreferences.Editor editor = mContext.getSharedPreferences(UserSystemConfig.USER_SYSTEM_OFFLINE_FILE, Context.MODE_PRIVATE).edit();
        editor.putString(UserSystemConfig.OfflineUserConfigFile.KEY_TOKEN, mUserToken);
        editor.putString(UserSystemConfig.OfflineUserConfigFile.KEY_USER_JSON, user_json);
        editor.putLong(UserSystemConfig.OfflineUserConfigFile.KEY_LOGIN_DATETIME, System.currentTimeMillis());
        editor.commit();
    }

    public String getUserToken(){
        if (TextUtils.isEmpty(mUserToken)) {
            SharedPreferences sf = mContext.getSharedPreferences(UserSystemConfig.USER_SYSTEM_OFFLINE_FILE, Context.MODE_PRIVATE);
            mUserToken = sf.getString(UserSystemConfig.OfflineUserConfigFile.KEY_TOKEN, "");
            return mUserToken;
        } else {
            return mUserToken;
        }
    }

    public String getUserJson(){
        SharedPreferences sf = mContext.getSharedPreferences(UserSystemConfig.USER_SYSTEM_OFFLINE_FILE, Context.MODE_PRIVATE);
        String user_json = sf.getString(UserSystemConfig.OfflineUserConfigFile.KEY_USER_JSON, "");
        return user_json;
    }

    public long getUserLoginDatetime(){
        SharedPreferences sf = mContext.getSharedPreferences(UserSystemConfig.USER_SYSTEM_OFFLINE_FILE, Context.MODE_PRIVATE);
        long login_datetime = sf.getLong(UserSystemConfig.OfflineUserConfigFile.KEY_LOGIN_DATETIME, -1);
        return login_datetime;
    }
}
