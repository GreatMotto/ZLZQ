package com.bm.zlzq.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.commodity.QuPinActivity;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.city.CitySelectActivity;
import com.bm.zlzq.home.message.MessageActivity;
import com.bm.zlzq.home.search.SearchActivity;
import com.bm.zlzq.home.zxing.activity.CaptureActivity;
import com.bm.zlzq.utils.DisplayUtil;
import com.bm.zlzq.view.AutomaticViewPager;

/**
 * Created by wangwm on 2015/12/3.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view, layout_header, view_one, view_two;
    private AutomaticViewPager view_pager;
    private LinearLayout dotLayout, ll_city, ll_scan, ll_msg, ll_search;
    private TextView tv_mai, tv_zu, tv_tc, tv_aqy, tv_chuang, tv_wanju, tv_kxp, tv_sh;
    private ListView lv_home;
    private HomeAdapter homeAdapter;
    private RadioGroup radiogroup;
    private RadioButton rb_one, rb_two;
    private int flag = 0;// 0买  1租
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
        view = inflater.inflate(R.layout.fg_home, container, false);
        initView();
        radiogroup.check(R.id.rb_one);
        return view;
    }

    private void initView() {
        lv_home = (ListView) view.findViewById(R.id.lv_home);
        ll_city = (LinearLayout) view.findViewById(R.id.ll_city);
        ll_scan = (LinearLayout) view.findViewById(R.id.ll_scan);
        ll_msg = (LinearLayout) view.findViewById(R.id.ll_msg);
        ll_search = (LinearLayout) view.findViewById(R.id.ll_search);

        layout_header = LayoutInflater.from(getActivity()).inflate(R.layout.hm_header, null);

        //banner图
        view_pager = (AutomaticViewPager) layout_header.findViewById(R.id.view_pager);
        dotLayout = (LinearLayout) layout_header.findViewById(R.id.dot_ll);

        //八个模块
        tv_mai = (TextView) layout_header.findViewById(R.id.tv_mai);
        tv_zu = (TextView) layout_header.findViewById(R.id.tv_zu);
        tv_tc = (TextView) layout_header.findViewById(R.id.tv_tc);
        tv_aqy = (TextView) layout_header.findViewById(R.id.tv_aqy);
        tv_chuang = (TextView) layout_header.findViewById(R.id.tv_chuang);
        tv_wanju = (TextView) layout_header.findViewById(R.id.tv_wanju);
        tv_kxp = (TextView) layout_header.findViewById(R.id.tv_kxp);
        tv_sh = (TextView) layout_header.findViewById(R.id.tv_sh);

        //TabHost
        rb_one = (RadioButton) layout_header.findViewById(R.id.rb_one);
        rb_two = (RadioButton) layout_header.findViewById(R.id.rb_two);
        radiogroup = (RadioGroup) layout_header.findViewById(R.id.radiogroup);
        view_one = layout_header.findViewById(R.id.view_one);
        view_two = layout_header.findViewById(R.id.view_two);

        ll_search.setOnClickListener(this);
        ll_city.setOnClickListener(this);
        ll_scan.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        tv_mai.setOnClickListener(this);
        tv_zu.setOnClickListener(this);
        tv_tc.setOnClickListener(this);
        tv_aqy.setOnClickListener(this);
        tv_chuang.setOnClickListener(this);
        tv_wanju.setOnClickListener(this);
        tv_kxp.setOnClickListener(this);
        tv_sh.setOnClickListener(this);

        lv_home.addHeaderView(layout_header);

        view_one.setVisibility(View.VISIBLE);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        view_one.setVisibility(View.VISIBLE);
                        view_two.setVisibility(View.GONE);
                        flag = 0;
                        homeAdapter = new HomeAdapter(getActivity(), flag);
                        lv_home.setAdapter(homeAdapter);
                        break;
                    case R.id.rb_two:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.VISIBLE);
                        flag = 1;
                        homeAdapter = new HomeAdapter(getActivity(), flag);
                        lv_home.setAdapter(homeAdapter);
                        break;
                    default:
                        break;
                }
            }
        });

        //初始化轮播图
        int width = DisplayUtil.getWidth(getActivity());
        DisplayUtil.setLayoutParams(view_pager, width, (int) (width / 2.2));
        view_pager.setClickFlag("1");
        view_pager.start(getActivity(), 4000, dotLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
                R.mipmap.hm_banner_selected, R.mipmap.hm_banner_unselected, ((BaseActivity) getActivity()).
                        getImageList(Constant.imageUrls, 4), 2.2f);
    }

    @Override
    public void onPause() {
        super.onPause();
        view_pager.stopTimer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_mai:
                ((BaseActivity) getActivity()).goto1OtherActivity(QuPinActivity.class, 0);
                break;
            case R.id.tv_zu:
                ((BaseActivity) getActivity()).goto1OtherActivity(QuPinActivity.class, 1);
                break;
            case R.id.tv_tc:
                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.tv_aqy:
                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.tv_chuang:
                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.tv_wanju:
                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.tv_kxp:
                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.tv_sh:
//                ((BaseActivity) getActivity()).gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.ll_city:
                ((BaseActivity) getActivity()).gotoOtherActivity(CitySelectActivity.class);
                break;
            case R.id.ll_scan:
                ((BaseActivity) getActivity()).gotoOtherActivity(CaptureActivity.class);
                break;
            case R.id.ll_msg:
                ((BaseActivity) getActivity()).gotoOtherActivity(MessageActivity.class);
                break;
            case R.id.ll_search:
                ((BaseActivity) getActivity()).gotoOtherActivity(SearchActivity.class);
                break;
            default:
                break;
        }
    }
}
