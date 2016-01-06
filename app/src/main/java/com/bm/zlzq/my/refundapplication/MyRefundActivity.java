package com.bm.zlzq.my.refundapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.TzsqBean;
import com.bm.zlzq.bean.TzsqBean_1;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 退租申请
 * Created by Administrator on 2015/12/19.
 */
public class MyRefundActivity extends BaseActivity {
    private List<TzsqBean> data = new ArrayList<TzsqBean>();
    private List<TzsqBean_1> data_1 = new ArrayList<TzsqBean_1>();
    private List<TzsqBean> data_temp = new ArrayList<TzsqBean>();
    private ImageView check_all_n;
    private ImageView check_all_y;
    private ImageView iv_search;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private RadioButton rb_4;
    private RadioGroup radiogroup_1;
    private View view_1;
    private View view_2;
    private View view_3;
    private View view_4;
    private TextView quit_buy;
    private TextView quit_price;
    private ListView lv_quit_tz_1;
    private RelativeLayout quit_check;
    private RelativeLayout quit;
    private TzsqAdapter adapter;
    private boolean isCheckAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_refund);
        initData();
        initView();
        initbind();
        initTitle("退租申请");
    }

    private void initbind() {
        rb_1.setOnClickListener(this);
        rb_2.setOnClickListener(this);
        rb_3.setOnClickListener(this);
        rb_4.setOnClickListener(this);
        quit_buy.setOnClickListener(this);
        quit_check.setOnClickListener(this);
        iv_search.setOnClickListener(this);

    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            TzsqBean msg = null;
            if (i % 3 == 0) {       //快递                                           //单价   //返还价格          //单个订单价格
                msg = new TzsqBean("顺丰", "id", false, "商品名称1", "x1", "20151229", "¥9.9", "¥99", "9g", "永久", "合计:￥990");
            } else if (i % 3 == 1) {
                msg = new TzsqBean("申通", "id", false, "商品名称2", "x2", "20151228", "¥9.8", "¥98", "9g", "永久", "合计:￥980");
            } else {
                msg = new TzsqBean("韵达", "id", false, "商品名称3", "x3", "20151227", "¥9.7", "¥97", "9g", "永久", "合计:￥970");
            }
            data.add(msg);
        }
        for (int i = 0; i < 3; i++) {
            TzsqBean_1 msg_1 = null;
            if (i % 3 == 0) {       //快递                                           //单价   //返还价格          //单个订单价格
                msg_1 = new TzsqBean_1("顺丰", "id", false, "商品名称1", "x1", "20151229", "¥9.9", "¥99", "9g", "永久", "合计:￥990");
            } else if (i % 3 == 1) {
                msg_1 = new TzsqBean_1("申通", "id", false, "商品名称2", "x2", "20151228", "¥9.8", "¥98", "9g", "永久", "合计:￥980");
            } else {
                msg_1 = new TzsqBean_1("韵达", "id", false, "商品名称3", "x3", "20151227", "¥9.7", "¥97", "9g", "永久", "合计:￥970");
            }
            data_1.add(msg_1);
        }

    }

    private void initView() {
        quit_buy = (TextView) findViewById(R.id.tv_quit_buy);

        quit_price = (TextView) findViewById(R.id.tv_quit_price);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        check_all_n = (ImageView) findViewById(R.id.iv_quit_check_all_n);
        check_all_y = (ImageView) findViewById(R.id.iv_quit_check_all_y);
        iv_search.setVisibility(View.VISIBLE);
        lv_quit_tz_1 = (ListView) findViewById(R.id.lv_quit_tz_1);
        quit = (RelativeLayout) findViewById(R.id.rl_quit);
        quit_check = (RelativeLayout) findViewById(R.id.rl_quit_check);

        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_1.setChecked(true);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        radiogroup_1 = (RadioGroup) findViewById(R.id.radiogroup_1);
        view_1 = findViewById(R.id.v_1);
        view_2 = findViewById(R.id.v_2);
        view_3 = findViewById(R.id.v_3);
        view_4 = findViewById(R.id.v_4);
        adapter = new TzsqAdapter(this, data, data_1, 1);
        lv_quit_tz_1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_search:
                gotoOtherActivity(SearchActivity.class);
                break;
            case R.id.rb_1:
                view_1.setVisibility(View.VISIBLE);
                view_2.setVisibility(View.GONE);
                view_3.setVisibility(View.GONE);
                view_4.setVisibility(View.GONE);
                quit.setVisibility(View.VISIBLE);
                adapter = new TzsqAdapter(this, data, data_1, 1);
                lv_quit_tz_1.setAdapter(adapter);
                break;
            case R.id.rb_2:
                view_1.setVisibility(View.GONE);
                view_2.setVisibility(View.VISIBLE);
                view_3.setVisibility(View.GONE);
                view_4.setVisibility(View.GONE);
                quit.setVisibility(View.GONE);
                adapter = new TzsqAdapter(this, data, data_1, 2);
                lv_quit_tz_1.setAdapter(adapter);
                break;
            case R.id.rb_3:
                view_1.setVisibility(View.GONE);
                view_2.setVisibility(View.GONE);
                view_3.setVisibility(View.VISIBLE);
                view_4.setVisibility(View.GONE);
                quit.setVisibility(View.GONE);
                adapter = new TzsqAdapter(this, data, data_1, 3);
                lv_quit_tz_1.setAdapter(adapter);
                break;
            case R.id.rb_4:
                view_1.setVisibility(View.GONE);
                view_2.setVisibility(View.GONE);
                view_3.setVisibility(View.GONE);
                view_4.setVisibility(View.VISIBLE);
                quit.setVisibility(View.GONE);
                adapter = new TzsqAdapter(this, data, data_1, 4);
                lv_quit_tz_1.setAdapter(adapter);
                break;
            case R.id.tv_quit_buy:
                Intent intent = new Intent(this, ReturnOrderActivity.class);
//                intent.putExtra();
//                intent.putExtra();
                startActivity(intent);
                break;
            //全选
            case R.id.rl_quit_check:
                if (isCheckAll) {
                    data_temp.clear();
                    check_all_y.setVisibility(View.GONE);
                    quit_buy.setText("结算");
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).ischeck = false;
                    }
                    isCheckAll = false;
                } else {
                    data_temp.clear();
                    data_temp.addAll(data);
                    quit_buy.setText("结算(" + data_temp.size() + ")");
                    check_all_y.setVisibility(View.VISIBLE);
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).ischeck = true;
                    }
                    isCheckAll = true;
                }
                adapter.notifyDataSetChanged();
                setNum();
                break;
            default:
                break;
        }
    }

    /*设置购物车总价格*/

    //每个订单额相加
    private void setNum() {

    }
}
