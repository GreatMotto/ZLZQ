package com.bm.zlzq.shopcar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.home.HomeActivity;

/**
 * Created by wangwm on 2015/12/15.
 */
public class FinishOrderActivity extends BaseActivity{
    private TextView tv_sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_finish_order);
        initView();
        initTitle("下单完成");
    }

    private void initView() {
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOtherActivity(HomeActivity.class);
            }
        });
    }
}
