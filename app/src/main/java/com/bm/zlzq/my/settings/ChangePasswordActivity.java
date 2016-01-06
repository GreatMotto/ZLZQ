package com.bm.zlzq.my.settings;

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
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.SharedPreferencesHelper;

/**
 * Created by Administrator on 2015/12/19.
 */
public class ChangePasswordActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_current_password, et_new_password, et_input_again;
    private TextView tv_sure;
    private SharedPreferencesHelper sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = ZLZQApplication.getInstance().getSp();
        setContentView(R.layout.ac_change_password);
        initView();
        initTitle("修改登录密码");
    }

    private void initView() {
        et_current_password = (EditText) findViewById(R.id.et_current_password);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        et_input_again = (EditText) findViewById(R.id.et_input_again);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        tv_sure.setOnClickListener(this);
    }

    private boolean canModify() {
        if (TextUtils.isEmpty(et_current_password.getText().toString().trim())) {
            NewToast.show(this, "请输入原始密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_new_password.getText().toString().trim())) {
            NewToast.show(this, "请输入新密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_input_again.getText().toString().trim())) {
            NewToast.show(this, "请再次输入", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_new_password.getText().toString().trim().equals(et_input_again.getText().toString().trim())) {
            NewToast.show(this, "两次输入密码不匹配", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_sure:
                if (canModify()) {

                    WebServiceAPI.getInstance().updatepassword(et_current_password.getText().toString().trim(), et_new_password.getText().toString().trim(), ChangePasswordActivity.this, ChangePasswordActivity.this);
                    Log.e("old", et_current_password.getText().toString().trim() + "0.0");
                    Log.e("new", et_new_password.getText().toString().trim() + "0.0");
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
