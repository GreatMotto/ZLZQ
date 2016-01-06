package com.bm.zlzq.my.myorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.MyOrderBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.shopcar.ConfirmOrderActivity;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by wangwm on 2015/12/18.
 */
public class MyOrderAdapter extends BaseAdapter {
    private Context context;
    private List<MyOrderBean> list;
    private ButtonClick buttonClick;
    private int flag = 0;// 0-我的订单  1-管理员订单

    public MyOrderAdapter(Context context, List<MyOrderBean> list, int flag, ButtonClick buttonClick) {
        this.context = context;
        this.list = list;
        this.flag = flag;
        this.buttonClick = buttonClick;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, null);
        }
        TextView tv_order_number = ViewHolder.get(convertView, R.id.tv_order_number);
        TextView tv_state = ViewHolder.get(convertView, R.id.tv_state);
        NoScrollListView nslv_goods = ViewHolder.get(convertView, R.id.nslv_goods);
        TextView tv_kuaidi = ViewHolder.get(convertView, R.id.tv_kuaidi);
        TextView tv_total_num = ViewHolder.get(convertView, R.id.tv_total_num);
        TextView tv_total_price = ViewHolder.get(convertView, R.id.tv_total_price);
        RelativeLayout rl_btn = ViewHolder.get(convertView, R.id.rl_btn);
        final TextView tv_black_btn = ViewHolder.get(convertView, R.id.tv_black_btn);
        final TextView tv_orange_btn = ViewHolder.get(convertView, R.id.tv_orange_btn);

        nslv_goods.setAdapter(new MyGoodsAdapter(context, list.get(position).goodslist));
        nslv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemPosition, long id) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra(Constant.ORDERNUMBER, list.get(position).ordernumber);
                intent.putExtra(Constant.LIST, (Serializable) list.get(position).goodslist);
                context.startActivity(intent);
            }
        });

        if (flag == 1) {
            tv_kuaidi.setVisibility(View.GONE);
        } else {
            tv_kuaidi.setVisibility(View.VISIBLE);
        }

        tv_order_number.setText(list.get(position).ordernumber);
        tv_state.setText(list.get(position).state);
        if (list.get(position).state.equals("交易结束") || list.get(position).state.equals("交货完成") || list.get(position).state.equals("退租完成")) {
            tv_state.setTextColor(Color.parseColor("#61d533"));
        } else {
            tv_state.setTextColor(Color.parseColor("#f18d00"));
        }

        // 计算数量和总价
        int totalNum = 0;
        float totalPrice = 0;
        for (int i = 0; i < list.get(position).goodslist.size(); i++) {
            int count = Integer.parseInt(list.get(position).goodslist.get(i).count);
            float price = Float.parseFloat(list.get(position).goodslist.get(i).priceTwo);
            totalNum += count;
            totalPrice += count * price;
        }
        tv_total_num.setText("共"+String.valueOf(totalNum)+"件商品");
        tv_total_price.setText("合计:￥" + new DecimalFormat("0.00").format(totalPrice));

        if (TextUtils.isEmpty(list.get(position).blkbtntext) && TextUtils.isEmpty(list.get(position).orgbtntext)) {
            rl_btn.setVisibility(View.GONE);
        } else {
            rl_btn.setVisibility(View.VISIBLE);
            tv_black_btn.setText(list.get(position).blkbtntext);
            tv_orange_btn.setText(list.get(position).orgbtntext);
        }
        tv_black_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_black_btn.getText().toString().equals("取消订单")) {
                    buttonClick.cancelClick(list.get(position));
                }
                if (tv_black_btn.getText().toString().equals("查看物流")) {

                }
                if (tv_black_btn.getText().toString().equals("续租")) {
                    Intent intent = new Intent(context, ReletActivity.class);
                    intent.putExtra(Constant.LIST, (Serializable) list.get(position).goodslist);
                    context.startActivity(intent);
                }
            }
        });
        tv_orange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_orange_btn.getText().toString().equals("付款")) {
                    Intent intent = new Intent(context, ConfirmOrderActivity.class);
                    intent.putExtra(Constant.FLAG, 2);
                    intent.putExtra(Constant.LIST, (Serializable) list.get(position).goodslist);
                    context.startActivity(intent);
                }
                if (tv_orange_btn.getText().toString().equals("确认收货")) {
                    buttonClick.confirmClick(list.get(position));
                }
                if (tv_orange_btn.getText().toString().equals("晒单评价")) {
                    Intent intent = new Intent(context, DisplayOrderActivity.class);
                    intent.putExtra(Constant.LIST, (Serializable) list.get(position).goodslist);
                    context.startActivity(intent);
                }
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra(Constant.ORDERNUMBER, list.get(position).ordernumber);
                intent.putExtra(Constant.LIST, (Serializable) list.get(position).goodslist);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public interface ButtonClick{
        void confirmClick(MyOrderBean comfirm);
        void cancelClick(MyOrderBean cancel);
    }
}
