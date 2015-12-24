package com.bm.zlzq.my.myorder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.MyOrderBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * Created by wangwm on 2015/12/18.
 */
public class MyOrderAdapter extends BaseAdapter{
    private Context context;
    private List<MyOrderBean> list;
    private int flag = 0;// 0-我的订单  1-管理员订单

    public MyOrderAdapter(Context context, List<MyOrderBean> list, int flag){
        this.context = context;
        this.list = list;
        this.flag = flag;
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, null);
        }
        TextView tv_order_number = ViewHolder.get(convertView, R.id.tv_order_number);
        TextView tv_state = ViewHolder.get(convertView, R.id.tv_state);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
        TextView tv_kuaidi = ViewHolder.get(convertView, R.id.tv_kuaidi);
        RelativeLayout rl_btn = ViewHolder.get(convertView, R.id.rl_btn);
        TextView tv_black_btn = ViewHolder.get(convertView, R.id.tv_black_btn);
        TextView tv_orange_btn = ViewHolder.get(convertView, R.id.tv_orange_btn);

        if (flag == 1){
            tv_kuaidi.setVisibility(View.GONE);
        }else {
            tv_kuaidi.setVisibility(View.VISIBLE);
        }

        tv_order_number.setText(list.get(position).ordernumber);
        tv_state.setText(list.get(position).state);
        if (list.get(position).state.equals("交易结束") || list.get(position).state.equals("交货完成") || list.get(position).state.equals("退租完成")){
            tv_state.setTextColor(Color.parseColor("#61d533"));
        }else {
            tv_state.setTextColor(Color.parseColor("#f18d00"));
        }
        tv_name.setText(list.get(position).name);
        tv_price.setText(list.get(position).price);
        if (TextUtils.isEmpty(list.get(position).blkbtntext) && TextUtils.isEmpty(list.get(position).orgbtntext)){
            rl_btn.setVisibility(View.GONE);
        }else {
            rl_btn.setVisibility(View.VISIBLE);
            tv_black_btn.setText(list.get(position).blkbtntext);
            tv_orange_btn.setText(list.get(position).orgbtntext);
        }

        return convertView;
    }
}
