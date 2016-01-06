package com.bm.zlzq.my.refundapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.NewToast;


public class VisitBackActivity extends BaseActivity {
    TextView sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit_back);
        initView();
        initbind();
        initTitle("填写快递信息");
    }

    private void initbind() {
        sure.setOnClickListener(this);
    }

    private void initView() {
        sure = (TextView) findViewById(R.id.tv_sure);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
//            case R.id.ll_master_back:
//                Intent intent = new Intent(this, ConfirmOrderActivity.class);
//                startActivity(intent);
//                break;
            case R.id.tv_sure:
                NewToast.show(this, "请设置好预约时间", NewToast.LENGTH_LONG);
                finish();
                break;
            default:
                break;
        }
    }


}
