package com.bm.zlzq.home.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/2.
 */
public class MsgAdapter extends BaseAdapter {
    private Context mContext;

    public MsgAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return 3;
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
            convertView = View.inflate(mContext, R.layout.lv_item_msg, null);
        }
        return convertView;
    }
}
