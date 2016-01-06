package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.SharedPreferencesHelper;

/**
 * Created by Administrator on 2015/12/19.
 */
public class ChangeMobileActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_check_password, et_new_mobile, et_verify_code;
    private TextView tv_send, tv_sure;
    private SharedPreferencesHelper sp;
    private boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = ZLZQApplication.getInstance().getSp();
        setContentView(R.layout.ac_change_moblie);
        initView();
        initTitle("修改手机号码");
    }

    private void initView() {
        et_check_password = (EditText) findViewById(R.id.et_check_password);
        et_new_mobile = (EditText) findViewById(R.id.et_new_mobile);
        et_verify_code = (EditText) findViewById(R.id.et_verify_code);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        tv_send.setOnClickListener(this);
        tv_sure.setOnClickListener(this);

    }

    private boolean canModify() {
        if (TextUtils.isEmpty(et_check_password.getText().toString().trim())) {
            NewToast.show(this, "请输入登录密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (et_check_password.getText().toString().trim().length() < 6) {
            NewToast.show(this, "登录密码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_new_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_new_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
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
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_send:
                NewToast.show(this, "验证码已发送", NewToast.LENGTH_LONG);
                break;
            case R.id.tv_sure:
                if (canModify()) {

                    WebServiceAPI.getInstance().updatephone(et_new_mobile.getText().toString().trim(), et_check_password.getText().toString().trim(), ChangeMobileActivity.this, ChangeMobileActivity.this);

//                  gotoOtherActivity(LoginActivity.class);
////                sp.remove(Constant.PASSWORD);
////                sp.remove(Constant.PHONE);
////                sp.remove(Constant.ISLOGIN);
                    sp.putValue(Constant.PASSWORD, "");
                    sp.putValue(Constant.PHONE, "");


                    onBackPressed();
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
        switch (tag) {
            case 0:
                ProgressUtils.cancleProgressDialog();
                NewToast.show(this, "修改成功!", NewToast.LENGTH_LONG);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
