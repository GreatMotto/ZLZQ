package com.bm.zlzq.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;

/**
 * Created by wangwm on 2015/12/1.
 */
public class RegisterActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_mobile, et_verify_code, et_invite_code, et_password, et_password_again;
    private TextView tv_send, tv_register, tv_agree;
    private ImageView iv_agree;
    private RelativeLayout rl_agree;
    private boolean isAgree = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_register);
        initTitle("注册");
        initView();
    }

    private void initView() {
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_verify_code = (EditText) findViewById(R.id.et_verify_code);
        et_invite_code = (EditText) findViewById(R.id.et_invite_code);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_agree = (TextView) findViewById(R.id.tv_agree);
        rl_agree = (RelativeLayout) findViewById(R.id.rl_agree);
        iv_agree = (ImageView) findViewById(R.id.iv_agree);

        tv_send.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_agree.setOnClickListener(this);
        rl_agree.setOnClickListener(this);
    }

    private boolean canRegister() {
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_verify_code.getText().toString().trim())) {
            NewToast.show(this, "请输入验证码", NewToast.LENGTH_LONG);
            return false;
        }
        if (et_verify_code.getText().toString().trim().length() < 6) {
            NewToast.show(this, "验证码不正确", NewToast.LENGTH_LONG);
            return false;
        }
//        if (TextUtils.isEmpty(et_invite_code.getText().toString().trim())) {
//            NewToast.show(this, "请输入邀请码", NewToast.LENGTH_LONG);
//            return false;
//        }
//        if (et_invite_code.getText().toString().trim().length() < 10) {
//            NewToast.show(this, "邀请码不正确", NewToast.LENGTH_LONG);
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
        if (isAgree) {
            NewToast.show(this, "未同意服务条款", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_send:
                //打开注册页面

//                RegisterPage registerPage = new RegisterPage();
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//// 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//
//// 提交用户信息
////                            registerUser(country, phone);
//                        }
//                    }
//                });
//                registerPage.show(this);


                NewToast.show(this, "验证码已发送", NewToast.LENGTH_LONG);
                break;
            case R.id.tv_register:
                if (canRegister()) {
                    ProgressUtils.showProgressDialog("", this);
                    WebServiceAPI.getInstance().register(et_mobile.getText().toString().trim(), et_password.getText().toString().trim(), "", RegisterActivity.this, RegisterActivity.this);
                }
                break;
            case R.id.rl_agree:
                clearfocus();
                if (isAgree) {
                    iv_agree.setVisibility(View.VISIBLE);
                    isAgree = false;
                } else {
                    iv_agree.setVisibility(View.GONE);
                    isAgree = true;
                }
                break;
            case R.id.tv_agree:
                gotoOtherActivity(ServiceTermsActivity.class);
                break;
            default:
                break;
        }
    }

    public void clearfocus() {
        et_mobile.clearFocus();
        et_verify_code.clearFocus();
        et_invite_code.clearFocus();
        et_password.clearFocus();
        et_password_again.clearFocus();
        CloseKeyboard();
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0")) {
            switch (tag) {
                case 0:
                    ProgressUtils.cancleProgressDialog();
                    gotoOtherActivity(LoginInActivity.class);
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
