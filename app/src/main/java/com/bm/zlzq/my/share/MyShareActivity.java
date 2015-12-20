package com.bm.zlzq.my.share;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyShareActivity extends BaseActivity{
    private RelativeLayout rl_sina,rl_weixin,rl_pyq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_share);
        initView();
        initTitle("我要分享");
    }

    private void initView() {
        rl_sina = (RelativeLayout) findViewById(R.id.rl_sina);
        rl_weixin = (RelativeLayout) findViewById(R.id.rl_weixin);
        rl_pyq = (RelativeLayout) findViewById(R.id.rl_pyq);

        rl_sina.setOnClickListener(this);
        rl_weixin.setOnClickListener(this);
        rl_pyq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rl_sina:

                break;
            case R.id.rl_weixin:

                break;
            case R.id.rl_pyq:

                break;
            default:
                break;
        }
    }
}
