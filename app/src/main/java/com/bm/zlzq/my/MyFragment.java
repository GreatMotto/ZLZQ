package com.bm.zlzq.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.my.address.MyAddressActivity;
import com.bm.zlzq.my.business.BusinessListActivity;
import com.bm.zlzq.my.collection.MyCollectionActivity;
import com.bm.zlzq.my.coupon.MyCouponActivity;
import com.bm.zlzq.my.myinfo.PersonalInfoActivity;
import com.bm.zlzq.my.myorder.MyOrderActivity;
import com.bm.zlzq.my.recharge.MyRechargeActivity;
import com.bm.zlzq.my.refundapplication.MyRefundActivity;
import com.bm.zlzq.my.settings.MySettingsActivity;
import com.bm.zlzq.my.share.MyShareActivity;

/**
 * Created by wangwm on 2015/12/3.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout rl_my_order;
    private ImageView iv_edit;
    private TextView tv_my_dfk, tv_my_dfh, tv_my_dsh, tv_my_ysh,
            tv_my_wddd, tv_my_wdsc, tv_my_wycz, tv_my_tzsq,
            tv_my_wyfx, tv_my_yhq, tv_my_shqd, tv_my_xtsz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fg_my, container, false);
        initView();
        return view;
    }

    private void initView() {
        iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
        rl_my_order = (RelativeLayout) view.findViewById(R.id.rl_my_order);

        tv_my_dfk = (TextView) view.findViewById(R.id.tv_my_dfk);
        tv_my_dfh = (TextView) view.findViewById(R.id.tv_my_dfh);
        tv_my_dsh = (TextView) view.findViewById(R.id.tv_my_dsh);
        tv_my_ysh = (TextView) view.findViewById(R.id.tv_my_ysh);

        tv_my_wddd = (TextView) view.findViewById(R.id.tv_my_wddd);
        tv_my_wdsc = (TextView) view.findViewById(R.id.tv_my_wdsc);
        tv_my_wycz = (TextView) view.findViewById(R.id.tv_my_wycz);
        tv_my_tzsq = (TextView) view.findViewById(R.id.tv_my_tzsq);
        tv_my_wyfx = (TextView) view.findViewById(R.id.tv_my_wyfx);
        tv_my_yhq = (TextView) view.findViewById(R.id.tv_my_yhq);
        tv_my_shqd = (TextView) view.findViewById(R.id.tv_my_shqd);
        tv_my_xtsz = (TextView) view.findViewById(R.id.tv_my_xtsz);


        iv_edit.setOnClickListener(this);
        rl_my_order.setOnClickListener(this);

        tv_my_dfk.setOnClickListener(this);
        tv_my_dfh.setOnClickListener(this);
        tv_my_dsh.setOnClickListener(this);
        tv_my_ysh.setOnClickListener(this);

        tv_my_wddd.setOnClickListener(this);
        tv_my_wdsc.setOnClickListener(this);
        tv_my_wycz.setOnClickListener(this);
        tv_my_tzsq.setOnClickListener(this);
        tv_my_wyfx.setOnClickListener(this);
        tv_my_yhq.setOnClickListener(this);
        tv_my_shqd.setOnClickListener(this);
        tv_my_xtsz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_edit:
                ((BaseActivity) getActivity()).gotoOtherActivity(PersonalInfoActivity.class);
                break;
            case R.id.rl_my_order:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 0);
                break;
            case R.id.tv_my_dfk:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 1);
                break;
            case R.id.tv_my_dfh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 2);
                break;
            case R.id.tv_my_dsh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 3);
                break;
            case R.id.tv_my_ysh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 4);
                break;
            case R.id.tv_my_wddd:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyAddressActivity.class);
                break;
            case R.id.tv_my_wdsc:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyCollectionActivity.class);
                break;
            case R.id.tv_my_wycz:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyRechargeActivity.class);
                break;
            case R.id.tv_my_tzsq:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyRefundActivity.class);
                break;
            case R.id.tv_my_wyfx:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyShareActivity.class);
                break;
            case R.id.tv_my_yhq:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyCouponActivity.class);
                break;
            case R.id.tv_my_shqd:
                ((BaseActivity) getActivity()).gotoOtherActivity(BusinessListActivity.class);
                break;
            case R.id.tv_my_xtsz:
                ((BaseActivity) getActivity()).gotoOtherActivity(MySettingsActivity.class);
                break;
            default:
                break;
        }
    }
}
