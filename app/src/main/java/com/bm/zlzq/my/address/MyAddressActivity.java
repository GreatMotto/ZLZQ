package com.bm.zlzq.my.address;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyAddressActivity extends BaseActivity{
    private TextView tv_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_address);
        initView();
        initTitle("管理收货地址");
    }

    private void initView() {
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setText("新增");
        tv_edit.setVisibility(View.VISIBLE);
    }
}
