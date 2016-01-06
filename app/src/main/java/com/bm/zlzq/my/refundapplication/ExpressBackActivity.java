package com.bm.zlzq.my.refundapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.NewToast;


public class ExpressBackActivity extends BaseActivity {
    TextView sure;
    ImageView img_sj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.express_back);
        initView();
        initbind();
        initTitle("填写快递信息");
    }

    private void initbind() {
        sure.setOnClickListener(this);
        img_sj.setOnClickListener(this);
    }

    private void initView() {
        sure = (TextView) findViewById(R.id.tv_sure);
        img_sj = (ImageView) findViewById(R.id.img_sj);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_sj:
//                Intent intent = new Intent(ExpressBackActivity.this, DialogActivity.class);
//                startActivity(intent);
                break;
            case R.id.tv_sure:
                NewToast.show(this, "请设置好预约时间", NewToast.LENGTH_LONG);
                finish();
                break;
            default:
                break;
        }
    }


}
