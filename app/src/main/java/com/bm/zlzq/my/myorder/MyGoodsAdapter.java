package com.bm.zlzq.my.myorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * Created by wangwm on 2015/12/28.
 */
public class MyGoodsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater mInflater;
    private List<ShopCarBean> list;

    public MyGoodsAdapter(Context context, List<ShopCarBean> list){
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
            convertView = mInflater.inflate(R.layout.item_goods, null);
        }
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);

        tv_name.setText(list.get(position).name);
        tv_price.setText("￥" + list.get(position).price);
        tv_num.setText("x" + list.get(position).count);

        return convertView;
    }
}
