package com.bm.zlzq.my.refundapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.home.search.SearchActivity;
import com.bm.zlzq.my.address.MyAddressActivity;
import com.bm.zlzq.utils.DialogUtil;


public class ReturnOrderActivity extends BaseActivity {
    private ImageView iv_search, img_daoqi, img_goumai, img_change, img_other;
    private TextView tv_daoqi, tv_change, tv_goumai, tv_other, tv_timer, account_msg, account_number;
    private LinearLayout master_back, express_back, visit_back, address;
    private RelativeLayout daoqi, change, goumai, other, timer, account_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_order);
        initView();
        initData();
        initbind();
        initTitle("退租申请");
    }

    private void initData() {

    }


    private void initbind() {
//        iv_search.setOnClickListener(this);
        master_back.setOnClickListener(this);
        express_back.setOnClickListener(this);
        visit_back.setOnClickListener(this);
        daoqi.setOnClickListener(this);
        change.setOnClickListener(this);
        goumai.setOnClickListener(this);
        other.setOnClickListener(this);
        address.setOnClickListener(this);
        timer.setOnClickListener(this);
        account_message.setOnClickListener(this);
    }

    private void initView() {
//        iv_search.setVisibility(View.VISIBLE);
        master_back = (LinearLayout) findViewById(R.id.ll_master_back);
        express_back = (LinearLayout) findViewById(R.id.ll_express_back);
        visit_back = (LinearLayout) findViewById(R.id.ll_visit_back);
        address = (LinearLayout) findViewById(R.id.ll_address);
        timer = (RelativeLayout) findViewById(R.id.rl_timer);
        account_message = (RelativeLayout) findViewById(R.id.rl_account_message);
        account_msg = (TextView) findViewById(R.id.tv_account_msg);
        daoqi = (RelativeLayout) findViewById(R.id.rl_daoqi);
        change = (RelativeLayout) findViewById(R.id.rl_change);
        goumai = (RelativeLayout) findViewById(R.id.rl_goumai);
        other = (RelativeLayout) findViewById(R.id.rl_other);
        img_daoqi = (ImageView) findViewById(R.id.img_daoqi);
        img_change = (ImageView) findViewById(R.id.img_change);
        img_goumai = (ImageView) findViewById(R.id.img_goumai);
        img_other = (ImageView) findViewById(R.id.img_other);
        tv_daoqi = (TextView) findViewById(R.id.tv_daoqi);
        tv_change = (TextView) findViewById(R.id.tv_change);
        tv_goumai = (TextView) findViewById(R.id.tv_goumai);
        tv_other = (TextView) findViewById(R.id.tv_other);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        account_number = (TextView) findViewById(R.id.tv_account_number);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_master_back:
                gotoOtherActivity(MasterBackActivity.class);
                break;
            case R.id.ll_express_back:
                gotoOtherActivity(ExpressBackActivity.class);
                break;
            case R.id.ll_visit_back:
                gotoOtherActivity(VisitBackActivity.class);
                break;
            case R.id.ll_address:
                gotoOtherActivity(MyAddressActivity.class);
                break;
            case R.id.rl_timer:
                DialogUtil.showDatePickDialog(this, tv_timer, 0);
                break;
            case R.id.rl_account_message:
                DialogUtil.shoePayDialog(this, account_msg, account_number);
                break;
            case R.id.rl_daoqi:
                img_daoqi.setImageResource(R.mipmap.gwc_xz);
                tv_daoqi.setTextColor(getResources().getColor(R.color.app_theme));
                img_change.setImageResource(R.mipmap.gwc_wxz);
                tv_change.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_goumai.setImageResource(R.mipmap.gwc_wxz);
                tv_goumai.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_other.setImageResource(R.mipmap.gwc_wxz);
                tv_other.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;
            case R.id.rl_change:
                img_change.setImageResource(R.mipmap.gwc_xz);
                tv_change.setTextColor(getResources().getColor(R.color.app_theme));
                img_daoqi.setImageResource(R.mipmap.gwc_wxz);
                tv_daoqi.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_goumai.setImageResource(R.mipmap.gwc_wxz);
                tv_goumai.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_other.setImageResource(R.mipmap.gwc_wxz);
                tv_other.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;
            case R.id.rl_goumai:
                img_goumai.setImageResource(R.mipmap.gwc_xz);
                tv_goumai.setTextColor(getResources().getColor(R.color.app_theme));
                img_other.setImageResource(R.mipmap.gwc_wxz);
                tv_other.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_daoqi.setImageResource(R.mipmap.gwc_wxz);
                tv_daoqi.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_change.setImageResource(R.mipmap.gwc_wxz);
                tv_change.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;
            case R.id.rl_other:
                img_other.setImageResource(R.mipmap.gwc_xz);
                tv_other.setTextColor(getResources().getColor(R.color.app_theme));
                img_daoqi.setImageResource(R.mipmap.gwc_wxz);
                tv_daoqi.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_change.setImageResource(R.mipmap.gwc_wxz);
                tv_change.setTextColor(getResources().getColor(R.color.qp_item_num));
                img_goumai.setImageResource(R.mipmap.gwc_wxz);
                tv_goumai.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;

            default:
                break;
        }
    }


}
