package com.bm.zlzq.my.manager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MyOrderBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.search.SearchActivity;
import com.bm.zlzq.my.myorder.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/24.
 */
public class MyManagerActivity extends BaseActivity {
    private View view_one, view_two;
    private ImageView iv_search;
    private RadioGroup radiogroup, rg_group;
    private RadioButton rb_one, rb_two, rb_first, rb_second, rb_third;
    private ListView lv_order;
    private MyOrderAdapter adapter;
    private List<MyOrderBean> list = new ArrayList<>();
    private int flag = 0;// 0-订单  1-退租
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_manager);
        title = getIntent().getStringExtra(Constant.TITLE);
        initView();
        initTitle(title);
    }

    private void initView() {
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setVisibility(View.VISIBLE);

        //TabHost
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);

        //BottomTab
        rg_group = (RadioGroup) findViewById(R.id.rg_group);
        rb_first = (RadioButton) findViewById(R.id.rb_first);
        rb_second = (RadioButton) findViewById(R.id.rb_second);
        rb_third = (RadioButton) findViewById(R.id.rb_third);

        lv_order = (ListView) findViewById(R.id.lv_order);

        //模拟订单数据
        final MyOrderBean db1 = new MyOrderBean();
        db1.ordernumber = "订单号：E5212120";
        db1.state = "待领取";
        db1.name = "进口蓝莓125g/份鲜果浆新鲜水果";
        db1.price = "￥3288.00";
        db1.blkbtntext = "查看物流";
        db1.orgbtntext = "确认收货";

        final MyOrderBean db2 = new MyOrderBean();
        db2.ordernumber = "订单号：E5212120";
        db2.state = "交货完成";
        db2.name = "进口蓝莓125g/份鲜果浆新鲜水果";
        db2.price = "￥3288.00";

        //模拟退租数据
        final MyOrderBean db3 = new MyOrderBean();
        db3.ordernumber = "订单号：E5212120";
        db3.state = "待确认";
        db3.name = "进口蓝莓125g/份鲜果浆新鲜水果";
        db3.price = "￥3288.00";
        db3.blkbtntext = "查看物流";
        db3.orgbtntext = "确认收货";

        final MyOrderBean db4 = new MyOrderBean();
        db4.ordernumber = "订单号：E5212120";
        db4.state = "退租完成";
        db4.name = "进口蓝莓125g/份鲜果浆新鲜水果";
        db4.price = "￥3288.00";

        list.add(db1);
        list.add(db2);
        adapter = new MyOrderAdapter(this, list, 1);
        lv_order.setAdapter(adapter);

        radiogroup.check(R.id.rb_one);
        rg_group.check(R.id.rb_first);
        view_one.setVisibility(View.VISIBLE);

        iv_search.setOnClickListener(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:// 订单
                        flag = 0;
                        rb_first.setText("全部");
                        rb_second.setText("待领取");
                        rb_third.setText("已交货");

                        Drawable first0 = getResources().getDrawable(R.drawable.mng_bottom_all);
                        first0.setBounds(0, 0, first0.getMinimumWidth(), first0.getMinimumHeight());
                        rb_first.setCompoundDrawables(null, first0, null, null);
                        Drawable second0 = getResources().getDrawable(R.drawable.mng_bottom_dailingqu);
                        second0.setBounds(0, 0, second0.getMinimumWidth(), second0.getMinimumHeight());
                        rb_second.setCompoundDrawables(null, second0, null, null);
                        Drawable third0 = getResources().getDrawable(R.drawable.mng_bottom_daiqueren);
                        third0.setBounds(0, 0, third0.getMinimumWidth(), third0.getMinimumHeight());
                        rb_third.setCompoundDrawables(null, third0, null, null);

                        rg_group.check(R.id.rb_first);
                        view_one.setVisibility(View.VISIBLE);
                        view_two.setVisibility(View.GONE);
                        list.clear();
                        list.add(db1);
                        list.add(db2);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_two:// 退租
                        flag = 1;
                        rb_first.setText("退租订单");
                        rb_second.setText("待确认");
                        rb_third.setText("已退租");

                        Drawable first1 = getResources().getDrawable(R.drawable.mng_bottom_tuizudingdan);
                        first1.setBounds(0, 0, first1.getMinimumWidth(), first1.getMinimumHeight());
                        rb_first.setCompoundDrawables(null, first1, null, null);
                        Drawable second1 = getResources().getDrawable(R.drawable.mng_bottom_daiqueren);
                        second1.setBounds(0, 0, second1.getMinimumWidth(), second1.getMinimumHeight());
                        rb_second.setCompoundDrawables(null, second1, null, null);
                        Drawable third1 = getResources().getDrawable(R.drawable.mng_bottom_yituizu);
                        third1.setBounds(0, 0, third1.getMinimumWidth(), third1.getMinimumHeight());
                        rb_third.setCompoundDrawables(null, third1, null, null);

                        rg_group.check(R.id.rb_first);
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.VISIBLE);
                        list.clear();
                        list.add(db3);
                        list.add(db4);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_first:// flag  0-全部  1-退租订单
                        list.clear();
                        if (flag == 0) {
                            list.add(db1);
                            list.add(db2);
                        } else {
                            list.add(db3);
                            list.add(db4);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_second:// flag  0-待领取  1-待确认
                        list.clear();
                        if (flag == 0) {
                            list.add(db1);
                        } else {
                            list.add(db3);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_third:// flag  0-已交货  1-已退租
                        list.clear();
                        if (flag == 0) {
                            list.add(db2);
                        } else {
                            list.add(db4);
                        }
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
