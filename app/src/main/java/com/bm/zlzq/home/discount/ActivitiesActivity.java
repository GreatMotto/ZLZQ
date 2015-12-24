package com.bm.zlzq.home.discount;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;

/**
 * Created by wangwm on 2015/12/3.
 */
public class ActivitiesActivity extends BaseActivity{
    private LinearLayout ll_goodsname;
    private ImageView iv_goods_image;
    private TextView tv_description;
    private int flag = 0;// 0-首页banner图跳转  1-商户详情跳转
    private String title = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_activities);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        title = getIntent().getStringExtra(Constant.TITLE);
        initView();
        if (flag == 0){
            initTitle("满减特惠");
        }else {
            initTitle(title);
            ll_goodsname.setVisibility(View.GONE);
        }
    }

    private void initView() {
        iv_goods_image = (ImageView) findViewById(R.id.iv_goods_image);
        ll_goodsname = (LinearLayout) findViewById(R.id.ll_goodsname);
        tv_description = (TextView) findViewById(R.id.tv_description);
    }
}
