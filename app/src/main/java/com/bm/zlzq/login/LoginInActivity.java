package com.bm.zlzq.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.HomeActivity;
import com.bm.zlzq.my.refundapplication.MyRefundActivity;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.SharedPreferencesHelper;

/**
 * Created by wangwm on 2015/12/1.
 */
public class LoginInActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_mobile, et_password;
    private TextView tv_login, tv_forget;
    private ImageView iv_wx, iv_wb, iv_qq;
    private SharedPreferencesHelper sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login_in);
        ZLZQApplication.getInstance().addActivity(this);
        sp = ZLZQApplication.getInstance().getSp();
        initView();
        initTitle("登录");
    }

    private void initView() {
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_wb = (ImageView) findViewById(R.id.iv_wb);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);

        tv_login.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        iv_wb.setOnClickListener(this);
        iv_qq.setOnClickListener(this);


    }

    private boolean canLogin() {
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            NewToast.show(this, "请输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_login:
//                gotoOtherActivity(HomeActivity.class);
                if (canLogin()) {
                    ProgressUtils.showProgressDialog("", this);
                    WebServiceAPI.getInstance().login(
                            et_mobile.getText().toString().trim(),
                            et_password.getText().toString().trim(), "", LoginInActivity.this, LoginInActivity.this);
                }
                break;
            case R.id.tv_forget:
                gotoOtherActivity(ForgetPasswordActivity.class);
                break;
            case R.id.iv_wx:
                gotoOtherActivity(HomeActivity.class);
                break;
            case R.id.iv_wb:
                gotoOtherActivity(HomeActivity.class);
                break;
            case R.id.iv_qq:
                gotoOtherActivity(HomeActivity.class);
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
                    gotoOtherActivity(HomeActivity.class);
                    sp.putValue(Constant.USERID, apiResponse.data.id);
                    sp.putValue(Constant.PHONE, et_mobile.getText().toString().trim());
                    sp.putValue(Constant.PASSWORD, et_password.getText().toString().trim());
                    sp.putBooleanValue(Constant.ISLOGIN, true);
                    Log.e("userid", apiResponse.data.id);
//                    Log.e("PHONE", et_mobile.getText().toString().trim() + "0.0");
//                    Log.e("PASSWORD", et_password.getText().toString().trim() + "0.0");

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