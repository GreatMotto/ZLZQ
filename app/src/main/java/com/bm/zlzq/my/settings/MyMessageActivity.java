package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/20.
 */
public class MyMessageActivity extends BaseActivity{
    private RelativeLayout rl_new_msg,rl_sound,rl_shock;
    private ImageView iv_new_msg_off,iv_new_msg_on,iv_sound_off,iv_sound_on,iv_shock_off,iv_shock_on;
    private boolean isNewMsg = true, isSound = true, isShock = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_message);
        initView();
        initTitle("消息");
    }

    private void initView() {
        rl_new_msg = (RelativeLayout) findViewById(R.id.rl_new_msg);
        rl_sound = (RelativeLayout) findViewById(R.id.rl_sound);
        rl_shock = (RelativeLayout) findViewById(R.id.rl_shock);

        iv_new_msg_off = (ImageView) findViewById(R.id.iv_new_msg_off);
        iv_new_msg_on = (ImageView) findViewById(R.id.iv_new_msg_on);

        iv_sound_off = (ImageView) findViewById(R.id.iv_sound_off);
        iv_sound_on = (ImageView) findViewById(R.id.iv_sound_on);

        iv_shock_off = (ImageView) findViewById(R.id.iv_shock_off);
        iv_shock_on = (ImageView) findViewById(R.id.iv_shock_on);

        rl_new_msg.setOnClickListener(this);
        rl_sound.setOnClickListener(this);
        rl_shock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rl_new_msg:
                if (isNewMsg){
                    iv_new_msg_off.setVisibility(View.VISIBLE);
                    iv_new_msg_on.setVisibility(View.GONE);
                }else {
                    iv_new_msg_off.setVisibility(View.GONE);
                    iv_new_msg_on.setVisibility(View.VISIBLE);
                }
                isNewMsg = !isNewMsg;
                break;
            case R.id.rl_sound:
                if (isSound){
                    iv_sound_off.setVisibility(View.VISIBLE);
                    iv_sound_on.setVisibility(View.GONE);
                }else {
                    iv_sound_off.setVisibility(View.GONE);
                    iv_sound_on.setVisibility(View.VISIBLE);
                }
                isSound = !isSound;
                break;
            case R.id.rl_shock:
                if (isShock){
                    iv_shock_off.setVisibility(View.VISIBLE);
                    iv_shock_on.setVisibility(View.GONE);
                }else {
                    iv_shock_off.setVisibility(View.GONE);
                    iv_shock_on.setVisibility(View.VISIBLE);
                }
                isShock = !isShock;
                break;
            default:
                break;
        }
    }
}
