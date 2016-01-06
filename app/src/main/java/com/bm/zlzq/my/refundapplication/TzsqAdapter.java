package com.bm.zlzq.my.refundapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.TzsqBean;
import com.bm.zlzq.bean.TzsqBean_1;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;

import java.util.List;


public class TzsqAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<TzsqBean> list;
    private List<TzsqBean_1> list_1;
    int flg;

    TextView wait_sure;
    TextView express_msg;
    TextView return_money;
    ImageView check_all;
    RelativeLayout rl_wait;
    TextView price_1;

    public TzsqAdapter(Context context, List<TzsqBean> list, List<TzsqBean_1> list_1, int flg) {
        this.context = context;
        this.list = list;
        this.list_1 = list_1;
        this.flg = flg;
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
            convertView = View.inflate(context, R.layout.item_tzsq, null);
        }
        //嵌套ListView
        NoScrollListView list_item_1 = ViewHolder.get(convertView, R.id.list_item_1);
        //订单号
        TextView order_number = ViewHolder.get(convertView, R.id.tv_order_number);
        order_number.setText(list.get(position).getOrderNumber());
        //返还金额
        price_1 = ViewHolder.get(convertView, R.id.tv_price_1);
        price_1.setText(list.get(position).getPrices());
        //每个订单价
        TextView total = ViewHolder.get(convertView, R.id.tv_total);
        total.setText(list.get(position).getTotalPrice());
        //数量
        TextView num = ViewHolder.get(convertView, R.id.tv_num);
//        num.setText(list.get(position).getNumber());


        wait_sure = ViewHolder.get(convertView, R.id.tv_wait_sure);
        express_msg = ViewHolder.get(convertView, R.id.tv_express_msg);
        check_all = ViewHolder.get(convertView, R.id.imv_check_all);

        rl_wait = ViewHolder.get(convertView, R.id.rl_wait);
        return_money = ViewHolder.get(convertView, R.id.tv_return_money);
        list_item_1.setAdapter(new TzsqAdapter_1(context, list_1, flg));
//        单个订单
        check_all.setTag(position);
        if ((list.get(position).ischeck) == true) {
            check_all.setImageResource(R.mipmap.gwc_xz);
        } else {
            check_all.setImageResource(R.mipmap.gwc_wxz);
        }

        // 单个商品选择
        check_all.setTag(position);
        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                if (list.get(pos).ischeck() == false) {
                    list.get(pos).setIscheck(true);
                    for (int i = 0; i < list_1.size(); i++) {
                        list_1.get(i).setIscheck(true);
                    }
                } else {
                    list.get(pos).setIscheck(false);
                    for (int i = 0; i < list_1.size(); i++) {
                        list_1.get(i).setIscheck(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        // 单个商品选择
        express_msg.setTag(position);
        express_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExpressBackActivity.class);
                context.startActivity(intent);
            }
        });
        if (flg == 1) {
            rl_wait.setVisibility(View.GONE);
            wait_sure.setVisibility(View.GONE);
        }
        if (flg == 2) {
            wait_sure.setVisibility(View.GONE);
            check_all.setVisibility(View.GONE);
            price_1.setVisibility(View.GONE);
            return_money.setVisibility(View.GONE);
            return_money.setVisibility(View.GONE);
        }
        if (flg == 3) {
            rl_wait.setVisibility(View.GONE);
            check_all.setVisibility(View.GONE);
            express_msg.setVisibility(View.GONE);
            return_money.setVisibility(View.GONE);
        }
        if (flg == 4) {
            check_all.setVisibility(View.GONE);
            express_msg.setVisibility(View.GONE);
            if (wait_sure.getText().equals("等待商家确认")) {
                wait_sure.setText("退租结束");
                wait_sure.setTextColor(Color.parseColor("#00CC33"));
            }
        }
        return convertView;
    }


    public void onClick(View v) {

    }

}



