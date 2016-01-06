package com.bm.zlzq.shopcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.AddressBean;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.my.address.MyAddressActivity;
import com.bm.zlzq.utils.DialogUtil;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;
import com.bm.zlzq.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 确认订单
 * Created by wangwm on 2015/12/14.
 */
public class ConfirmOrderActivity extends BaseActivity {
    private ImageView iv_chongzhika, iv_alipay, iv_yinlian;
    private RelativeLayout rl_chongzhika, rl_alipay, rl_yinlian, rl_send_way, rl_coupon;
    private LinearLayout ll_send_way, ll_merchant, ll_address;
    private TextView tv_heji_price, tv_zongji_price, tv_total_num, tv_sure,
            tv_chongzhika, tv_alipay, tv_yinlian, tv_send_way,
            tv_name, tv_mobile, tv_address;
    private NoScrollListView listView;
    private AddressBean addressBean = new AddressBean();
    private float zongjiPrice = 0;
    private List<ShopCarBean> list = new ArrayList<>();
    private int isfirst = 0;
    private boolean havepay;
    private static final int RESULT_CONFIRM = 3;
    private int flag = 0;// 0-购物车  1-续租  2-付款

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_confirm_order);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        list = (List<ShopCarBean>) getIntent().getSerializableExtra(Constant.LIST);
        initView();
        if (flag == 1) {
            initTitle("确认续租");
        } else {
            initTitle("确认订单");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isfirst == 1) {
            DialogUtil.showZiTiDialog(this, clickInterface);
        }
    }

    private void initView() {
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_address = (TextView) findViewById(R.id.tv_address);

        listView = (NoScrollListView) findViewById(R.id.nslv_goods);
        tv_heji_price = (TextView) findViewById(R.id.tv_heji_price);
        tv_zongji_price = (TextView) findViewById(R.id.tv_zongji_price);
        tv_total_num = (TextView) findViewById(R.id.tv_total_num);
        rl_chongzhika = (RelativeLayout) findViewById(R.id.rl_chongzhika);
        rl_alipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        rl_yinlian = (RelativeLayout) findViewById(R.id.rl_yinlian);
        iv_chongzhika = (ImageView) findViewById(R.id.iv_chongzhika);
        iv_alipay = (ImageView) findViewById(R.id.iv_alipay);
        iv_yinlian = (ImageView) findViewById(R.id.iv_yinlian);
        tv_chongzhika = (TextView) findViewById(R.id.tv_chongzhika);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        tv_yinlian = (TextView) findViewById(R.id.tv_yinlian);
        ll_merchant = (LinearLayout) findViewById(R.id.ll_merchant);
        ll_send_way = (LinearLayout) findViewById(R.id.ll_send_way);
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
        ll_address.setOnClickListener(this);

        if (flag == 1) {
            ll_merchant.setVisibility(View.VISIBLE);
            ll_send_way.setVisibility(View.GONE);
        }

        // 计算数量和合计
        int totalNum = 0;
        float totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            int count = Integer.parseInt(list.get(i).count);
            float price = Float.parseFloat(list.get(i).priceTwo);
            totalNum += count;
            totalPrice += count * price;
        }
        tv_total_num.setText("共" + String.valueOf(totalNum) + "件商品");
        tv_heji_price.setText("¥" + new DecimalFormat("0.00").format(totalPrice));
        zongjiPrice = totalPrice + 15;
        tv_zongji_price.setText("¥" + new DecimalFormat("0.00").format(zongjiPrice));

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_CONFIRM:
                    addressBean = (AddressBean) data.getSerializableExtra("address");
                    tv_name.setText("取货人姓名：" + addressBean.consignee);
                    tv_mobile.setText(addressBean.mobile);
                    tv_address.setText("收货地址：" + addressBean.area + addressBean.street + addressBean.address);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_address:
                Intent intent = new Intent(this, MyAddressActivity.class);
                intent.putExtra(Constant.FLAG, 1);
                startActivityForResult(intent, RESULT_CONFIRM);
                break;
            case R.id.rl_chongzhika:
                iv_chongzhika.setImageResource(R.mipmap.gwc_xz);
                iv_alipay.setImageResource(R.mipmap.gwc_wxz);
                iv_yinlian.setImageResource(R.mipmap.gwc_wxz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.app_theme));
                tv_alipay.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_yinlian.setTextColor(getResources().getColor(R.color.qp_item_num));
                havepay = true;
                break;
            case R.id.rl_alipay:
                iv_chongzhika.setImageResource(R.mipmap.gwc_wxz);
                iv_alipay.setImageResource(R.mipmap.gwc_xz);
                iv_yinlian.setImageResource(R.mipmap.gwc_wxz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_alipay.setTextColor(getResources().getColor(R.color.app_theme));
                tv_yinlian.setTextColor(getResources().getColor(R.color.qp_item_num));
                havepay = true;
                break;
            case R.id.rl_yinlian:
                iv_chongzhika.setImageResource(R.mipmap.gwc_wxz);
                iv_alipay.setImageResource(R.mipmap.gwc_wxz);
                iv_yinlian.setImageResource(R.mipmap.gwc_xz);
                tv_chongzhika.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_alipay.setTextColor(getResources().getColor(R.color.qp_item_num));
                tv_yinlian.setTextColor(getResources().getColor(R.color.app_theme));
                havepay = true;
                break;
            case R.id.rl_send_way:
                DialogUtil.showDialog(this, tv_send_way, clickInterface);
                break;
            case R.id.rl_coupon:
                gotoOtherActivity(OrderCouponActivity.class);
                break;
            case R.id.tv_sure:
                if (havepay) {
                    gotoOtherActivity(FinishOrderActivity.class);
                } else {
                    NewToast.show(this, "请选择支付方式", NewToast.LENGTH_LONG);
                }
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
            RoundImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(iv_image.getLayoutParams());
            params.setMargins(16, 0, 0, 0);
            iv_image.setLayoutParams(params);

            iv_check.setVisibility(View.GONE);
            if (TextUtils.isEmpty(list.get(position).priceTwo)) {
                tv_price.setText("¥0.00");
            } else {
                tv_price.setText("¥" + list.get(position).priceTwo);
            }

            ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, ((BaseActivity) context).getImageOptions());
            tv_name.setText(list.get(position).name);
            tv_num.setText("x" + list.get(position).count);
            return convertView;
        }
    }
}
