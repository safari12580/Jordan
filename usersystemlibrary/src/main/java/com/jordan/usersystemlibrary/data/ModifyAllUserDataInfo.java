package com.jordan.usersystemlibrary.data;

import com.jordan.httplibrary.utils.data.JsonInfo;
import com.jordan.usersystemlibrary.config.UserSystemConfig;

import org.json.JSONObject;

/**
 * Created by icean on 2017/2/2.
 */

public final class ModifyAllUserDataInfo extends JsonInfo {
    private String mName, mNick, mGender, mAge, mBirthday, mPosition, mWeight, mHeight, mQQ, mImg;

    public ModifyAllUserDataInfo(String name, String nick, String gender, String age,
                                 String birthday, String position, String weight, String height, String QQ, String img) {
        mName = name;
        mNick = nick;
        mGender = gender;
        mAge = age;
        mBirthday = birthday;
        mPosition = position;
        mWeight = weight;
        mHeight = height;
        mQQ = QQ;
        mImg = img;
    }

    public String getName() {
        return mName;
    }

    public String getNick() {
        return mNick;
    }

    public String getGender() {
        return mGender;
    }

    public String getAge() {
        return mAge;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public String getPosition() {
        return mPosition;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getQQ() {
        return mQQ;
    }

    public String getImg() {
        return mImg;
    }

    @Override
    public String toJsonStr() {
        try {
            JSONObject json_object = new JSONObject();
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_NAME, mName);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_NICK, mNick);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_GENDER, mGender);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_AGE, mAge);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_BIRTHDAY, mBirthday);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_POSITION, mPosition);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_WEIGHT, mWeight);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_HEIGHT, mHeight);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_QQ, mQQ);
            json_object.put(UserSystemConfig.ModifyInfoAllMessageConfig.JSON_REQUEST_IMG_URL, mImg);
            String result_json = json_object.toString();
            return  result_json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "ModifyAllUserDataInfo{" +
                "mName='" + mName + '\'' +
                ", mNick='" + mNick + '\'' +
                ", mGender='" + mGender + '\'' +
                ", mAge='" + mAge + '\'' +
                ", mBirthday='" + mBirthday + '\'' +
                ", mPosition='" + mPosition + '\'' +
                ", mWeight='" + mWeight + '\'' +
                ", mHeight='" + mHeight + '\'' +
                ", mQQ='" + mQQ + '\'' +
                ", mImg='" + mImg + '\'' +
                '}';
    }
}
