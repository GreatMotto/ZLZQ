package com.bm.zlzq.my.refundapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * 退租申请
 * Created by Administrator on 2015/12/19.
 */
public class MyRefundActivity extends BaseActivity {
    private ImageView iv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_refund);
        initView();
        initTitle("退租申请");
    }

    private void initView() {
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setVisibility(View.VISIBLE);
    }
}
