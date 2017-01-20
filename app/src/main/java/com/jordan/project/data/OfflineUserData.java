package com.jordan.project.data;

/**
 * Created by icean on 2017/1/20.
 */

public final class OfflineUserData {

    private String mUserName, mUserToken, mUserPhone, mUserEmail, mUserNick;
    private long mLastLoginDateTime;

    public OfflineUserData(String user_name, String user_token, String user_phone,
                           String user_email, String user_nick, long last_login_datetime) {
        mUserName = user_name;
        mUserToken = user_token;
        mUserPhone = user_phone;
        mUserEmail = user_email;
        mUserNick = user_nick;
        mLastLoginDateTime = last_login_datetime;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserToken() {
        return mUserToken;
    }

    public String getUserPhone() {
        return mUserPhone;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public String getUserNick() {
        return mUserNick;
    }

    public long getLastLoginDateTime() {
        return mLastLoginDateTime;
    }

    @Override
    public String toString() {
        return "OfflineUserData{" +
                "mUserName='" + mUserName + '\'' +
                ", mUserToken='" + mUserToken + '\'' +
                ", mUserPhone='" + mUserPhone + '\'' +
                ", mUserEmail='" + mUserEmail + '\'' +
                ", mUserNick='" + mUserNick + '\'' +
                ", mLastLoginDateTime=" + mLastLoginDateTime +
                '}';
    }
}
