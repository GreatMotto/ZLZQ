package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.NewToast;

/**
 * Created by Administrator on 2015/12/19.
 */
public class ChangePasswordActivity extends BaseActivity{
    private EditText et_current_password, et_new_password,et_input_again;
    private TextView tv_sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                if (canModify()){
                    onBackPressed();
                }
                break;
            default:
                break;
        }
    }
}
