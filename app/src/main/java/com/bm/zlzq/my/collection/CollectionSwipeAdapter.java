
package com.bm.zlzq.my.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.CollectionBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;


public class CollectionSwipeAdapter extends BaseAdapter {
    /**
     * 上下文对象
     */
    private Context mContext = null;

    /**
     * 初始化RightView的宽度
     */
    private int mRightWidth = 0;

    /**
     * collection list item
     */
    private List<CollectionBean> list;

    /**
     * 单击事件监听器
     */
    private IOnItemClickListener mListener = null;

    public interface IOnItemClickListener {
        void onRightClick(View v, int position);
    }

    public CollectionSwipeAdapter(Context ctx, int rightWidth, List<CollectionBean> list, IOnItemClickListener l) {
        this.mContext = ctx;
        this.mRightWidth = rightWidth;
        this.list = list;
        this.mListener = l;
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int thisPosition = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.swp_lv_item_collection, parent, false);
        }
        View item_left = ViewHolder.get(convertView, R.id.item_left);
        View item_right = ViewHolder.get(convertView, R.id.item_right);
        TextView item_right_text = ViewHolder.get(convertView, R.id.item_right_txt);

        TextView tv_goodsname = ViewHolder.get(convertView, R.id.tv_goodsname);
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);


        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp1.topMargin = 20;
        item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        lp2.topMargin = 20;
        item_right.setLayoutParams(lp2);
//
        tv_goodsname.setText(list.get(position).goodsname);
        tv_price.setText(list.get(position).price);
        item_right_text.setText("取消收藏");


        item_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightClick(v, thisPosition);
                }
            }
        });
        return convertView;
    }
}
