package com.bm.zlzq.my.myorder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.bean.MyOrderBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 * Created by wangwm on 2015/12/18.
 */
public class MyOrderActivity extends BaseActivity {
    private View view_one, view_two, view_three, view_four, view_five;
    private ImageView iv_search;
    private RadioGroup radiogroup;
    private RadioButton rb_one, rb_two, rb_three, rb_four, rb_five;
    private ListView lv_order;
    private MyOrderAdapter adapter;
    private List<MyOrderBean> list = new ArrayList<>();
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_order);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        initView();
        initTitle("我的订单");
    }

    private void initView() {
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setVisibility(View.VISIBLE);
        lv_order = (ListView) findViewById(R.id.lv_order);

        //TabHost
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        rb_three = (RadioButton) findViewById(R.id.rb_three);
        rb_four = (RadioButton) findViewById(R.id.rb_four);
        rb_five = (RadioButton) findViewById(R.id.rb_five);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);
        view_three = findViewById(R.id.view_three);
        view_four = findViewById(R.id.view_four);
        view_five = findViewById(R.id.view_five);

        //模拟订单数据--------------------------
        final MyOrderBean db1 = new MyOrderBean();
        for (int i = 0; i < 2; i++) {
            ShopCarBean scb = new ShopCarBean();
            scb.name = "进口蓝莓125g/份鲜果浆新鲜水果";
            scb.price = "3288.00";
            scb.count = "2";
            db1.goodslist.add(scb);
        }
        db1.ordernumber = "订单号：E5212120";
        db1.state = "等待买家付款";
        db1.blkbtntext = "取消订单";
        db1.orgbtntext = "付款";
        list.add(db1);

        final MyOrderBean db2 = new MyOrderBean();
        db2.ordernumber = "订单号：E5212120";
        db2.state = "等待卖家发货";
        for (int i = 0; i < 2; i++) {
            ShopCarBean scb = new ShopCarBean();
            scb.name = "进口蓝莓125g/份鲜果浆新鲜水果";
            scb.price = "3288.00";
            scb.count = "2";
            db2.goodslist.add(scb);
        }
        db2.blkbtntext = "取消订单";
        db2.orgbtntext = "确认发货";
        list.add(db2);

        final MyOrderBean db3 = new MyOrderBean();
        db3.ordernumber = "订单号：E5212120";
        db3.state = "等待买家确认收货";
        for (int i = 0; i < 2; i++) {
            ShopCarBean scb = new ShopCarBean();
            scb.name = "进口蓝莓125g/份鲜果浆新鲜水果";
            scb.price = "3288.00";
            scb.count = "2";
            db3.goodslist.add(scb);
        }
        db3.blkbtntext = "查看物流";
        db3.orgbtntext = "确认收货";
        list.add(db3);

        final MyOrderBean db4 = new MyOrderBean();
        db4.ordernumber = "订单号：E5212120";
        db4.state = "交易结束";
        for (int i = 0; i < 3; i++) {
            ShopCarBean scb = new ShopCarBean();
            scb.name = "进口蓝莓125g/份鲜果浆新鲜水果";
            scb.price = "3288.00";
            scb.count = "1";
            scb.isCheck = false;
            db4.goodslist.add(scb);
        }
        db4.blkbtntext = "续租";
        db4.orgbtntext = "晒单评价";
        list.add(db4);
        //-----------------------------------------------

        adapter = new MyOrderAdapter(this, list, 0);
        lv_order.setAdapter(adapter);

        if (flag == 0){
            radiogroup.check(R.id.rb_one);
            view_one.setVisibility(View.VISIBLE);
        } else if (flag == 1){
            radiogroup.check(R.id.rb_two);
            view_one.setVisibility(View.GONE);
            view_two.setVisibility(View.VISIBLE);
            view_three.setVisibility(View.GONE);
            view_four.setVisibility(View.GONE);
            view_five.setVisibility(View.GONE);
            list.clear();
            list.add(db1);
            adapter.notifyDataSetChanged();
        } else if (flag == 2){
            radiogroup.check(R.id.rb_three);
            view_one.setVisibility(View.GONE);
            view_two.setVisibility(View.GONE);
            view_three.setVisibility(View.VISIBLE);
            view_four.setVisibility(View.GONE);
            view_five.setVisibility(View.GONE);
            list.clear();
            list.add(db2);
            adapter.notifyDataSetChanged();
        } else if (flag == 3){
            radiogroup.check(R.id.rb_four);
            view_one.setVisibility(View.GONE);
            view_two.setVisibility(View.GONE);
            view_three.setVisibility(View.GONE);
            view_four.setVisibility(View.VISIBLE);
            view_five.setVisibility(View.GONE);
            list.clear();
            list.add(db3);
            adapter.notifyDataSetChanged();
        } else if (flag == 4){
            radiogroup.check(R.id.rb_five);
            view_one.setVisibility(View.GONE);
            view_two.setVisibility(View.GONE);
            view_three.setVisibility(View.GONE);
            view_four.setVisibility(View.GONE);
            view_five.setVisibility(View.VISIBLE);
            list.clear();
            list.add(db4);
            adapter.notifyDataSetChanged();
        }

        iv_search.setOnClickListener(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        view_one.setVisibility(View.VISIBLE);
                        view_two.setVisibility(View.GONE);
                        view_three.setVisibility(View.GONE);
                        view_four.setVisibility(View.GONE);
                        view_five.setVisibility(View.GONE);
                        list.clear();
                        list.add(db1);
                        list.add(db2);
                        list.add(db3);
                        list.add(db4);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_two:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.VISIBLE);
                        view_three.setVisibility(View.GONE);
                        view_four.setVisibility(View.GONE);
                        view_five.setVisibility(View.GONE);
                        list.clear();
                        list.add(db1);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_three:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.GONE);
                        view_three.setVisibility(View.VISIBLE);
                        view_four.setVisibility(View.GONE);
                        view_five.setVisibility(View.GONE);
                        list.clear();
                        list.add(db2);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_four:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.GONE);
                        view_three.setVisibility(View.GONE);
                        view_four.setVisibility(View.VISIBLE);
                        view_five.setVisibility(View.GONE);
                        list.clear();
                        list.add(db3);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_five:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.GONE);
                        view_three.setVisibility(View.GONE);
                        view_four.setVisibility(View.GONE);
                        view_five.setVisibility(View.VISIBLE);
                        list.clear();
                        list.add(db4);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_search:
                gotoOtherActivity(SearchActivity.class);
                break;
            default:
                break;
        }
    }
}
