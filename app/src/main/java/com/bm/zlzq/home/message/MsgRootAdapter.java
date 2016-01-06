package com.bm.zlzq.home.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.MessageBean;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;

import java.util.List;

/**
 * Created by wangwm on 2015/12/2.
 *
 */
public class MsgRootAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<MessageBean> list;

    public MsgRootAdapter(Context mContext, List<MessageBean> list) {
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
            convertView = inflater.inflate(R.layout.lv_item_msg_root, null);
        }
        TextView tv_date = ViewHolder.get(convertView, R.id.tv_date);
        tv_date.setText(list.get(position).createTime);

        NoScrollListView nslv_msg = ViewHolder.get(convertView, R.id.nslv_msg);
        nslv_msg.setAdapter(new MsgAdapter(mContext, list.get(position).contentList));
        return convertView;
    }
}
