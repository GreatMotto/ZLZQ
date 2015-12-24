package com.bm.zlzq.my.merchant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/23.
 */
public class MerchantListAdapter extends BaseAdapter{
    private Activity context;
    private LayoutInflater mInflater;
    private List<MerchantBean> list = new ArrayList<>();

    public MerchantListAdapter(Activity context, List<MerchantBean> list){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.item_merchant, null);
        }
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_have_goods = ViewHolder.get(convertView, R.id.tv_have_goods);
        TextView tv_distance = ViewHolder.get(convertView, R.id.tv_distance);
        TextView tv_phone_number = ViewHolder.get(convertView, R.id.tv_phone_number);
        TextView tv_address = ViewHolder.get(convertView, R.id.tv_address);

        tv_have_goods.setVisibility(View.GONE);
        tv_phone_number.setVisibility(View.VISIBLE);

        tv_name.setText(list.get(position).name);
        tv_phone_number.setText(list.get(position).phonenumber);
        tv_distance.setText(list.get(position).distance);
        tv_address.setText(list.get(position).address);

        return convertView;
    }
}
