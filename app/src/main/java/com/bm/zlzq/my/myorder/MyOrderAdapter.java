package com.bm.zlzq.my.myorder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public MyOrderAdapter(Context context, List<MyOrderBean> list){
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
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_order, null);
        }
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_state = ViewHolder.get(convertView, R.id.tv_state);
        TextView tv_black_btn = ViewHolder.get(convertView, R.id.tv_black_btn);
        TextView tv_orange_btn = ViewHolder.get(convertView, R.id.tv_orange_btn);

        tv_state.setText(list.get(position).state);
        tv_black_btn.setText(list.get(position).blkbtntext);
        tv_orange_btn.setText(list.get(position).orgbtntext);

        return convertView;
    }
}
