package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/19.
 */
public class AccountAndSecurityActivity extends BaseActivity{
    private RelativeLayout rl_change_mobile,rl_change_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_account_and_security);
        initView();
        initTitle("账户与安全");
    }

    private void initView() {
        rl_change_mobile = (RelativeLayout) findViewById(R.id.rl_change_mobile);
        rl_change_password = (RelativeLayout) findViewById(R.id.rl_change_password);

        rl_change_mobile.setOnClickListener(this);
        rl_change_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rl_change_mobile:
                gotoOtherActivity(ChangeMobileActivity.class);
                break;
            case R.id.rl_change_password:
                gotoOtherActivity(ChangePasswordActivity.class);
                break;
            default:
                break;
        }
    }
}
