package com.bm.zlzq.my.refundapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.TzsqBean;
import com.bm.zlzq.bean.TzsqBean_1;
import com.bm.zlzq.utils.ViewHolder;

import java.util.List;


public class TzsqAdapter_1 extends BaseAdapter implements View.OnClickListener {

    private Context context;

    private List<TzsqBean_1> list_1;
    int flg;

    TextView wait_sure;
    TextView express_msg;
    TextView return_money;
    ImageView check_all;
    RelativeLayout rl_wait;
    ImageView check;
    TextView price_1;

    public TzsqAdapter_1(Context context, List<TzsqBean_1> list_1, int flg) {
        this.context = context;
        this.list_1 = list_1;
        this.flg = flg;
    }

    @Override
    public int getCount() {
        return list_1.size();
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
            convertView = View.inflate(context, R.layout.item_tzsq_1, null);
        }
        //商品名称
        TextView names = ViewHolder.get(convertView, R.id.tv_name);
        names.setText(list_1.get(position).getName());

        //规格
        TextView standards = ViewHolder.get(convertView, R.id.tv_standard);
        standards.setText(list_1.get(position).getStandard());
        //租期
        TextView rend_date = ViewHolder.get(convertView, R.id.tv_rend_date);
        rend_date.setText(list_1.get(position).getTimes());
        //单价
        TextView price = ViewHolder.get(convertView, R.id.tv_price);
        price.setText(list_1.get(position).getPrice());
        //数量
        TextView num = ViewHolder.get(convertView, R.id.tv_num);
        num.setText(list_1.get(position).getNumber());
        check = ViewHolder.get(convertView, R.id.imv_check);
//        单个商品
        check.setTag(position);
        if ((list_1.get(position).ischeck) == true) {
            check.setImageResource(R.mipmap.gwc_xz);
        } else {
            check.setImageResource(R.mipmap.gwc_wxz);
        }
        check.setTag(position);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("check", "点击了1");
                int pos = (Integer) v.getTag();
                for (int i = 0; i < list_1.size(); i++) {
                    if (i == pos) {
                        if (list_1.get(i).ischeck() == false) {
                            list_1.get(i).setIscheck(true);
                        } else {
                            list_1.get(i).setIscheck(false);
                        }

                    }

                }
                notifyDataSetChanged();
            }
        });

        if (flg == 2 || flg == 3 || flg == 4) {
            check.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void onClick(View v) {

    }

}



