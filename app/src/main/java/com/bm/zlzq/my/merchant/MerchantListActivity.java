package com.bm.zlzq.my.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.discount.ActivitiesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MerchantListActivity extends BaseActivity {
    private LinearLayout ll_back;
    private ListView lv_merchant;
    private TextView tv_sousuo;
    private List<MerchantBean> list = new ArrayList<>();
    private MerchantListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_merchant_list);
        initView();
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        lv_merchant = (ListView) findViewById(R.id.lv_merchant);
        tv_sousuo = (TextView) findViewById(R.id.tv_sousuo);
        tv_sousuo.setText("筛选");

        ll_back.setOnClickListener(this);
        tv_sousuo.setOnClickListener(this);

        //模拟商户数据
        for (int i = 0; i < 10; i++) {
            MerchantBean db = new MerchantBean();
            db.name = "宝隆便利店 " + i;
            db.distance = "23km";
            db.phonenumber = "电话:020-555555";
            db.address = "地址:徐州市中山南路169号";
            list.add(db);
        }
        adapter = new MerchantListAdapter(this, list);
        lv_merchant.setAdapter(adapter);
        lv_merchant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MerchantListActivity.this, ActivitiesActivity.class);
                intent.putExtra(Constant.FLAG, 1);
                intent.putExtra(Constant.TITLE, list.get(position).name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.tv_sousuo:

                break;
            default:
                break;
        }
    }
}
