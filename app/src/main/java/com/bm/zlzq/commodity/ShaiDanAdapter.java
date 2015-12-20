package com.bm.zlzq.commodity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.zlzq.R;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollGridView;

/**
 * Created by wangwm on 2015/12/10.
 */
public class ShaiDanAdapter extends BaseAdapter{
    private Context context;

    public ShaiDanAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView = View.inflate(context, R.layout.item_shaidan, null);
        }
        NoScrollGridView gridView = ViewHolder.get(convertView, R.id.gridview);
        ImageGridAdapter adapter = new ImageGridAdapter(context, null);
        gridView.setAdapter(adapter);
        return convertView;
    }
}
