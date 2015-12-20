package com.bm.zlzq.my.recharge;

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
public class MyRechargeActivity extends BaseActivity{
    private EditText et_card_num, et_password;
    private TextView tv_sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_recharge);
        initView();
        initTitle("我要充值");
    }

    private void initView() {
        et_card_num = (EditText) findViewById(R.id.et_card_num);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        tv_sure.setOnClickListener(this);
    }

    private boolean canSubmit() {
        if (TextUtils.isEmpty(et_card_num.getText().toString().trim())) {
            NewToast.show(this, "请输入卡号", NewToast.LENGTH_LONG);
            return false;
        }
        if (et_card_num.getText().toString().trim().length() < 19) {
            NewToast.show(this, "卡号不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            NewToast.show(this, "请输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (et_password.getText().toString().trim().length() < 6) {
            NewToast.show(this, "密码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_sure:
                if (canSubmit()){
                    onBackPressed();
                }
                break;
            default:
                break;
        }
    }
}
