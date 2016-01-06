package com.bm.zlzq.commodity;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.ProductType;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;

/**
 * TypeGridView
 */
public class TypeGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<ProductType> list;
    private LayoutInflater inflater;
    private BoxCheck boxCheck;

    public TypeGridAdapter(Context context, List<ProductType> list, BoxCheck boxCheck) {
        this.mContext = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.boxCheck = boxCheck;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gv_item_type, null);
        }
        CheckBox cb_item = ViewHolder.get(convertView, R.id.cb_item);
        cb_item.setText(list.get(position).name);
        cb_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    boxCheck.check(position);
                }else {
                    boxCheck.noCheck(position);
                }
            }
        });
        return convertView;
    }

    public interface BoxCheck{
        void check(int position);
        void noCheck(int position);
    }
}
