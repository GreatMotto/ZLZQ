package com.bm.zlzq.home.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.zlzq.R;
import com.bm.zlzq.view.NoScrollListView;

/**
 * Created by wangwm on 2015/12/2.
 *
 */
public class MsgRootAdapter extends BaseAdapter {
    private Context mContext;

    public MsgRootAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return 2;
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
            convertView = View.inflate(mContext, R.layout.lv_item_msg_root, null);
            NoScrollListView nslv_msg = (NoScrollListView) convertView.findViewById(R.id.nslv_msg);
            nslv_msg.setAdapter(new MsgAdapter(mContext));
        }
        return convertView;
    }
}
