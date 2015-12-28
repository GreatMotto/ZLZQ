package com.bm.zlzq.my.myorder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.WheelDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/28.
 */
public class ReletAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<ShopCarBean> list;
    private List<String> datelist = new ArrayList<String>();
    private CheckInterface checkInterface;


    public ReletAdapter(Context context, List<ShopCarBean> list, CheckInterface checkInterface) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.checkInterface = checkInterface;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shpcat_item, null);
        }
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
        final ImageView iv_check = ViewHolder.get(convertView, R.id.iv_check);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
        View view_line = ViewHolder.get(convertView, R.id.view_line);
        RelativeLayout rl_relet_date = ViewHolder.get(convertView, R.id.rl_relet_date);
        final TextView tv_relet_date = ViewHolder.get(convertView, R.id.tv_relet_date);

        rl_relet_date.setVisibility(View.VISIBLE);
        view_line.setVisibility(View.VISIBLE);

        tv_name.setText(list.get(position).name);
        tv_price.setText("￥" + list.get(position).price);
        tv_num.setText("x" + list.get(position).count);
        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isCheck) {
                    checkInterface.onClick(position, false);
                } else {
                    checkInterface.onClick(position, true);
                }
            }
        });
        if (list.get(position).isCheck) {
            iv_check.setImageResource(R.mipmap.gwc_xz);
        } else {
            iv_check.setImageResource(R.mipmap.gwc_wxz);
        }

        rl_relet_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datelist.clear();
                datelist.add("1个月");
                datelist.add("2个月");
                datelist.add("3个月");
                datelist.add("4个月");
                datelist.add("5个月");
                datelist.add("6个月");
                WheelDialog.getInstance().ChossDateOrNumDlg((Activity) context, "续租期", tv_relet_date, datelist, new WheelDialog.GetCityIdListener() {
                    @Override
                    public void GetCityId(String provinceId, String cityId, String areaId) {

                    }
                });
            }
        });
        return convertView;
    }

    public interface CheckInterface {
        public void onClick(int position, boolean checked);
    }
}
