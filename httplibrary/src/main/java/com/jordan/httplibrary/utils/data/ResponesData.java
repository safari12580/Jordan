package com.jordan.httplibrary.utils.data;

public class ResponesData {
    private String mResultCode, mMessage;

    public ResponesData(String ResultCode, String Message) {
        mResultCode = ResultCode;
        mMessage = Message;
    }

    public String getResultCode() {
        return mResultCode;
    }

    public String getMessage() {
        return mMessage;
    }

    @Override
    public String toString() {
        return "ResponesData{" +
                "mResultCode='" + mResultCode + '\'' +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}
