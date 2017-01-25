package com.jordan.project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jordan.project.R;
import com.jordan.project.config.ActivityActionConfig;
import com.jordan.project.utils.ActivityUtils;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mETPhone, mETCode, mETPassword, mETRePassword;
    private String mCurrentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mCurrentPhone = ActivityUtils.getInputStrInfo(getIntent(), ActivityActionConfig.KEY_LOGIN_USER_NAME);

        mETPhone = (EditText)findViewById(R.id.fp_phone_number_et);
        if (TextUtils.isEmpty(mCurrentPhone)) {
            mETPhone.setText(mCurrentPhone);
        }
        mETCode = (EditText)findViewById(R.id.fp_author_code_et);
        mETPassword = (EditText)findViewById(R.id.fp_password_et);
        mETRePassword = (EditText)findViewById(R.id.fp_re_password_et);

        Button confirm_btn = (Button) findViewById(R.id.register_confirm_btn);
        confirm_btn.setOnClickListener(this);
        Button cancel_btn = (Button) findViewById(R.id.fp_cancel_btn);
        cancel_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fp_confirm_btn:
                break;
            case R.id.fp_cancel_btn:
                setResult(ActivityActionConfig.RESULT_CODE_FORGET_PASSWORD_CANCEL);
                finish();
                break;
            default:
                throw new RuntimeException("nonsupport operation");
        }
    }

}
