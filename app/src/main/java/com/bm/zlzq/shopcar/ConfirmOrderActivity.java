package com.bm.zlzq.shopcar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.DialogUtil;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/14.
 */
public class ConfirmOrderActivity extends BaseActivity {
    private ImageView iv_chongzhika, iv_alipay, iv_yinlian;
    private RelativeLayout rl_chongzhika, rl_alipay, rl_yinlian, rl_send_way, rl_coupon;
    private TextView tv_price, tv_zongji_price, tv_sure, tv_chongzhika,tv_alipay,tv_yinlian,tv_send_way;
    private NoScrollListView listView;
    private List<ShopCarBean> list = new ArrayList<>();
    private String price;
    private float totalPrice;
    private int isfirst = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_confirm_order);
        list = (List<ShopCarBean>) getIntent().getSerializableExtra(Constant.CARLIST);
        price = getIntent().getStringExtra(Constant.PRICE);
        initView();
        initTitle("确认订单");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isfirst == 1){
            DialogUtil.showZiTiDialog(this, clickInterface);
        }
    }

    private void initView() {
        listView = (NoScrollListView) findViewById(R.id.nslv_goods);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_zongji_price = (TextView) findViewById(R.id.tv_zongji_price);
        rl_chongzhika = (RelativeLayout) findViewById(R.id.rl_chongzhika);
        rl_alipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        rl_yinlian = (RelativeLayout) findViewById(R.id.rl_yinlian);
        iv_chongzhika = (ImageView) findViewById(R.id.iv_chongzhika);
        iv_alipay = (ImageView) findViewById(R.id.iv_alipay);
        iv_yinlian = (ImageView) findViewById(R.id.iv_yinlian);
        tv_chongzhika = (TextView) findViewById(R.id.tv_chongzhika);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        tv_yinlian = (TextView) findViewById(R.id.tv_yinlian);
        rl_send_way = (RelativeLayout) findViewById(R.id.rl_send_way);
        tv_send_way = (TextView) findViewById(R.id.tv_send_way);
        rl_coupon = (RelativeLayout) findViewById(R.id.rl_coupon);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        rl_chongzhika.setOnClickListener(this);
        rl_alipay.setOnClickListener(this);
        rl_yinlian.setOnClickListener(this);
        rl_send_way.setOnClickListener(this);
        rl_coupon.setOnClickListener(this);
        tv_sure.setOnClickListener(this);

        tv_price.setText(price);
        if (Float.parseFloat(price.substring(1)) != 0) {
            totalPrice = Float.parseFloat(price.substring(1)) + 15;
        }
        tv_zongji_price.setText("¥" + String.valueOf(totalPrice) + "0");
        listView.setAdapter(new MyOrderAdapter(this, list));
    }

    AdapterClickInterface clickInterface = new AdapterClickInterface() {
        @Override
        public void onClick(String s) {

        }

        @Override
        public void isFirst(int flag) {
            isfirst = flag;
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_chongzhika:
                iv_chongzhika.setImageResource(R.mipmap.gwc_xz);
                iv_alipay.setImageResource(R.mipmap.gwc_wxz);
                iv_yinlian.setImageResource(R.mipmap.gwc_wxz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.app_theme));
                tv_alipay.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_yinlian.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;
            case R.id.rl_alipay:
                iv_chongzhika.setImageResource(R.mipmap.gwc_wxz);
                iv_alipay.setImageResource(R.mipmap.gwc_xz);
                iv_yinlian.setImageResource(R.mipmap.gwc_wxz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_alipay.setTextColor(getResources().getColor(R.color.app_theme));
                tv_yinlian.setTextColor(getResources().getColor(R.color.qp_item_num));
                break;
            case R.id.rl_yinlian:
                iv_chongzhika.setImageResource(R.mipmap.gwc_wxz);
                iv_alipay.setImageResource(R.mipmap.gwc_wxz);
                iv_yinlian.setImageResource(R.mipmap.gwc_xz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_alipay.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_yinlian.setTextColor(getResources().getColor(R.color.app_theme));
                break;
            case R.id.rl_send_way:
                DialogUtil.showDialog(this, tv_send_way,clickInterface);
                break;
            case R.id.rl_coupon:
                gotoOtherActivity(OrderCouponActivity.class);
                break;
            case R.id.tv_sure:
                gotoOtherActivity(FinishOrderActivity.class);
                break;
            default:
                break;
        }
    }

    public class MyOrderAdapter extends BaseAdapter {
        private Context context;
        private List<ShopCarBean> list;

        public MyOrderAdapter(Context context, List<ShopCarBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.shpcat_item, null);
            }
            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
            TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
            TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
            ImageView iv_check = ViewHolder.get(convertView, R.id.iv_check);
            ImageView iv_pic = ViewHolder.get(convertView, R.id.iv_pic);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(iv_pic.getLayoutParams());
            params.setMargins(16, 0, 0, 0);
            iv_pic.setLayoutParams(params);

            iv_check.setVisibility(View.GONE);
            if (list.get(position).equals("")) {
                tv_price.setText("¥" + list.get(position).price + "0.00");
            } else if (list.get(position).price.contains(".")) {
                tv_price.setText("¥" + list.get(position).price);
            } else {
                tv_price.setText("¥" + list.get(position).price + ".00");
            }
            tv_name.setText(list.get(position).pname);
            tv_num.setText("x" + list.get(position).count);
            return convertView;
        }
    }
}
