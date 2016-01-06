package com.bm.zlzq.home.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.Content;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * Created by wangwm on 2015/12/2.
 */
public class MsgAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Content> list;

    public MsgAdapter(Context mContext, List<Content> list) {
        super();
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv_item_msg, null);
        }
        TextView tv_msg = ViewHolder.get(convertView, R.id.tv_msg);
        tv_msg.setText(list.get(position).content);
        return convertView;
    }
}
