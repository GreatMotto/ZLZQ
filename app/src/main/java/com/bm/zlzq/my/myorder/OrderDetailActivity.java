package com.bm.zlzq.my.myorder;

import android.os.Bundle;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情
 * Created by wangwm on 2015/12/28.
 */
public class OrderDetailActivity extends BaseActivity {
    private TextView tv_order_number, tv_name, tv_mobile,
            tv_address, tv_merchant, tv_pay_way, tv_goods_price,
            tv_send_price, tv_coupon_price, tv_actual_price;
    private NoScrollListView nslv_goods;
    private List<ShopCarBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_order_detail);
        list = (List<ShopCarBean>) getIntent().getSerializableExtra(Constant.RELETLIST);
        initView();
        initTitle("订单详情");
    }

    private void initView() {
        tv_order_number = (TextView) findViewById(R.id.tv_order_number);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_merchant = (TextView) findViewById(R.id.tv_merchant);
        tv_pay_way = (TextView) findViewById(R.id.tv_pay_way);
        tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_send_price = (TextView) findViewById(R.id.tv_send_price);
        tv_coupon_price = (TextView) findViewById(R.id.tv_coupon_price);
        tv_actual_price = (TextView) findViewById(R.id.tv_actual_price);
        nslv_goods = (NoScrollListView) findViewById(R.id.nslv_goods);
        nslv_goods.setAdapter(new MyGoodsAdapter(this, list));
    }
}
