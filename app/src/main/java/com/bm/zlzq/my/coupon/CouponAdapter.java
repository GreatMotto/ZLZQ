package com.bm.zlzq.my.coupon;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.CouponBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * Created by wangwm on 2015/12/23.
 */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<CouponBean> list;
    private String isGet = "0";
    private boolean fromOrder;

    public CouponAdapter(Context context, List<CouponBean> list, boolean fromOrder) {
        this.context = context;
        this.list = list;
        this.fromOrder = fromOrder;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon, null);
        }

        TextView tv_end_date = ViewHolder.get(convertView, R.id.tv_end_date);
        LinearLayout ll_text_pre = ViewHolder.get(convertView, R.id.ll_text_pre);
        TextView tv_price_1 = ViewHolder.get(convertView, R.id.tv_price_1);
        TextView tv_price_2 = ViewHolder.get(convertView, R.id.tv_price_2);
        TextView tv_text_2 = ViewHolder.get(convertView, R.id.tv_text_2);
        TextView tv_get = ViewHolder.get(convertView, R.id.tv_get);

        tv_end_date.setText(list.get(position).enddate);

        if (list.get(position).isget.equals("0")){
            tv_get.setVisibility(View.GONE);
        }else {
            tv_get.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(list.get(position).fullprice)){
            ll_text_pre.setVisibility(View.GONE);
            tv_price_2.setText(list.get(position).minusprice);
            tv_text_2.setText("元优惠券");
        }else {
            ll_text_pre.setVisibility(View.VISIBLE);
            tv_price_1.setText(list.get(position).fullprice);
            tv_price_2.setText(list.get(position).minusprice);
            tv_text_2.setText("元");
        }

        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromOrder){
                    ((BaseActivity) context).onBackPressed();
                }
            }
        });
        return convertView;
    }
}
