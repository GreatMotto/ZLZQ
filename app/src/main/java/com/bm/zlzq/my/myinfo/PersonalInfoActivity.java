package com.bm.zlzq.my.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/18.
 */
public class PersonalInfoActivity extends BaseActivity {
    private RelativeLayout rl_touxiang, rl_nickname, rl_sex, rl_address;
    private TextView tv_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_personal_info);
        initView();
        initTitle("个人资料");
    }

    private void initView() {
        rl_touxiang = (RelativeLayout) findViewById(R.id.rl_touxiang);
        rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_address = (RelativeLayout) findViewById(R.id.rl_address);

        rl_nickname.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_nickname:
//                gotoOtherActivity(ModifyNicknameActivity.class);
                Intent intent = new Intent(this, ModifyNicknameActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String nickname = data.getStringExtra("nickname");
            switch (requestCode) {
                case 1:
                    tv_nickname.setText(nickname);
                    break;
                default:
                    break;
            }
        }
    }
}
