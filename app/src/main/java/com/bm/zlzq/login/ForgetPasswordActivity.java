package com.bm.zlzq.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.SharedPreferencesHelper;

/**
 * Created by wangwm on 2015/12/2.
 */
public class ForgetPasswordActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_mobile, et_verify_code, et_password, et_password_again;
    private TextView tv_send, tv_submit;
    private SharedPreferencesHelper sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_forget_password);
        initTitle("忘记密码");
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_verify_code = (EditText) findViewById(R.id.et_verify_code);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

        tv_send.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    private boolean canSubmit() {
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号码不正确", NewToast.LENGTH_LONG);
            return false;
        }
//        if (TextUtils.isEmpty(et_verify_code.getText().toString().trim())) {
//            NewToast.show(this, "请输入验证码", NewToast.LENGTH_LONG);
//            return false;
//        }
//        if (et_verify_code.getText().toString().trim().length() < 6) {
//            NewToast.show(this, "验证码不正确", NewToast.LENGTH_LONG);
//            return false;
//        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            NewToast.show(this, "请输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password_again.getText().toString().trim())) {
            NewToast.show(this, "请再次输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_password.getText().toString().trim().equals(et_password_again.getText().toString().trim())) {
            NewToast.show(this, "两次输入密码不匹配", NewToast.LENGTH_LONG);
            return false;
        }
        WebServiceAPI.getInstance().updpassword(et_mobile.getText().toString().trim(), et_password.getText().toString().trim(), ForgetPasswordActivity.this, ForgetPasswordActivity.this);
        Log.e("et_password", et_password.getText().toString() + "...");
        Log.e("et_password_again", et_password_again.getText().toString() + "...");
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_send:
                NewToast.show(this, "验证码已发送", NewToast.LENGTH_LONG);
                break;
            case R.id.tv_submit:
                if (canSubmit()) {
                    NewToast.show(this, "密码重置成功", NewToast.LENGTH_LONG);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    ProgressUtils.cancleProgressDialog();

//                    Log.e("userid", );
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
