package com.bm.zlzq.home.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * Created by wangwm on 2015/12/10.
 */
public class SearchAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;

    public SearchAdapter(Context context, List<String> list){
        this.list = list;
        this.context = context;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_search, null);
        }
        TextView tv_history = ViewHolder.get(convertView, R.id.tv_history);
        tv_history.setText(list.get(position));
        return convertView;
    }
}
