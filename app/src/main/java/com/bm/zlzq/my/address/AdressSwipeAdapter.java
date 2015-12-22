
package com.bm.zlzq.my.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.AddressBean;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;


public class AdressSwipeAdapter extends BaseAdapter {
    /**
     * 上下文对象
     */
    private Context mContext = null;

    /**
     * 初始化RightView的宽度
     */
    private int mRightWidth = 0;

    /**
     * address list item
     */
    private List<AddressBean> list;

    /**
     * 单击事件监听器
     */
    private IOnItemClickListener mListener = null;

    public interface IOnItemClickListener {
        void onRightClick(View v, int position);

        void onDefaultAdrs(View v, int position);

        void onDelClick(View v, int position);

        void onEditClick(View v, int position);
    }

    public AdressSwipeAdapter(Context ctx, int rightWidth, List<AddressBean> list, IOnItemClickListener l) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.swp_lv_item, parent, false);
        }
        View item_left = ViewHolder.get(convertView, R.id.item_left);
        View item_right = ViewHolder.get(convertView, R.id.item_right);
        TextView item_right_text = ViewHolder.get(convertView, R.id.item_right_txt);

        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_mobile = ViewHolder.get(convertView, R.id.tv_mobile);
        TextView tv_address = ViewHolder.get(convertView, R.id.tv_address);
        RelativeLayout rl_set_default = ViewHolder.get(convertView, R.id.rl_set_default);
        final ImageView iv_default = ViewHolder.get(convertView, R.id.iv_default);
        final TextView tv_default = ViewHolder.get(convertView, R.id.tv_default);
        TextView tv_edit = ViewHolder.get(convertView, R.id.tv_edit);
        TextView tv_delete = ViewHolder.get(convertView, R.id.tv_delete);


        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp1.topMargin = 24;
        item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        lp2.topMargin = 24;
        item_right.setLayoutParams(lp2);
//
        tv_name.setText(list.get(position).name);
        tv_mobile.setText(list.get(position).mobile);
        tv_address.setText(list.get(position).area + list.get(position).street + list.get(position).detailaddress);
        item_right_text.setText("删除");

        if (list.get(position).isDefault.equals("1")) {
            iv_default.setImageResource(R.mipmap.gwc_xz);
            tv_default.setText("默认地址");
        } else {
            iv_default.setImageResource(R.mipmap.gwc_wxz);
            tv_default.setText("设为默认");
        }
        rl_set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDefaultAdrs(v, thisPosition);
                }
            }
        });

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onEditClick(v, thisPosition);
                }
            }
        });

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDelClick(v, thisPosition);
                }
            }
        });

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
