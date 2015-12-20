package com.bm.zlzq.commodity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.shopcar.ShopCarActivity;
import com.bm.zlzq.utils.DisplayUtil;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.view.AutomaticViewPager;
import com.bm.zlzq.view.NoScrollGridView;
import com.bm.zlzq.view.SlidingMenu;
import com.bm.zlzq.view.WheelDialog;
import com.bm.zlzq.view.YsnowScrollView;
import com.bm.zlzq.view.YsnowWebView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangwm on 2015/12/4.
 */
public class QuPinDetailActivity extends BaseActivity{
    private AutomaticViewPager view_pager;
    private LinearLayout dotLayout, ll_collect, ll_detail_price_old;
    private NoScrollGridView gridview;
    private ImageGridAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private RelativeLayout rl_rend_date, rl_guige, rl_num;
    private TextView tv_add_shopcar, tv_rend_now, tv_rend_date, tv_num, tv_detail_price, tv_detail_price_old, tv_detail_yajin;
    private View v_rend_line, view_one, view_two;
    private ImageView iv_collect;
    private boolean isCollected;
    private RadioGroup radiogroup;
    private RadioButton rb_one, rb_two;
    private SlidingMenu sliding_menu;
    private YsnowWebView ysnowwebview;
    private YsnowScrollView ysnowscrollview;
    private int i = 0;
    //    private YsnowListView ysnowslistview;
//    private ShaiDanAdapter shaiDanAdapter;
    private int flag = 0;// 0买  1租

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_qp_detail);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        initTitle("商品详情");
        initView();
        radiogroup.check(R.id.rb_one);
    }

    private void initView() {
        sliding_menu = (SlidingMenu) findViewById(R.id.sliding_menu);
        ysnowwebview = (YsnowWebView) findViewById(R.id.ysnowswebview);
        ysnowscrollview = (YsnowScrollView) findViewById(R.id.ysnowscrollview);
        tv_add_shopcar = (TextView) findViewById(R.id.tv_add_shopcar);
        tv_rend_now = (TextView) findViewById(R.id.tv_rend_now);
//        ysnowslistview = (YsnowListView) findViewById(R.id.ysnowslistview);
        //banner图
        view_pager = (AutomaticViewPager) findViewById(R.id.view_pager);
        dotLayout = (LinearLayout) findViewById(R.id.dot_ll);

        //收藏
        ll_collect = (LinearLayout) findViewById(R.id.ll_collect);
        iv_collect = (ImageView) findViewById(R.id.iv_collect);
        //价格
        tv_detail_price = (TextView) findViewById(R.id.tv_detail_price);
        ll_detail_price_old = (LinearLayout) findViewById(R.id.ll_detail_price_old);
        tv_detail_price_old = (TextView) findViewById(R.id.tv_detail_price_old);
        tv_detail_yajin = (TextView) findViewById(R.id.tv_detail_yajin);

        //租赁期
        rl_rend_date = (RelativeLayout) findViewById(R.id.rl_rend_date);
        tv_rend_date = (TextView) findViewById(R.id.tv_rend_date);
        v_rend_line = findViewById(R.id.v_rend_line);
        //规格
        rl_guige = (RelativeLayout) findViewById(R.id.rl_guige);
        //数量
        rl_num = (RelativeLayout) findViewById(R.id.rl_num);
        tv_num = (TextView) findViewById(R.id.tv_num);

        //晒单区
        gridview = (NoScrollGridView) findViewById(R.id.gridview);

        //TabHost
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);

        if (flag == 1) {
            tv_detail_price.setText("租赁单价:138/月");
            tv_detail_yajin.setVisibility(View.VISIBLE);
            ll_detail_price_old.setVisibility(View.GONE);
            rl_rend_date.setVisibility(View.VISIBLE);
        } else {
            tv_detail_price.setText("购买价格:99.90-256.00");
            tv_detail_yajin.setVisibility(View.GONE);
            ll_detail_price_old.setVisibility(View.VISIBLE);
            tv_detail_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            rl_rend_date.setVisibility(View.GONE);
            v_rend_line.setVisibility(View.GONE);
        }

        ll_collect.setOnClickListener(this);
        rl_rend_date.setOnClickListener(this);
        rl_guige.setOnClickListener(this);
        rl_num.setOnClickListener(this);
        tv_add_shopcar.setOnClickListener(this);
        tv_rend_now.setOnClickListener(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        view_one.setVisibility(View.VISIBLE);
                        view_two.setVisibility(View.GONE);
                        ysnowwebview.setVisibility(View.VISIBLE);
                        ysnowscrollview.setVisibility(View.GONE);
                        flag = 0;
                        break;
                    case R.id.rb_two:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.VISIBLE);
                        ysnowwebview.setVisibility(View.GONE);
                        ysnowscrollview.setVisibility(View.VISIBLE);
                        flag = 1;
                        break;
                    default:
                        break;
                }
            }
        });

        //初始化轮播图
        int width = DisplayUtil.getWidth(this);
        DisplayUtil.setLayoutParams(view_pager, width, (int) (width / 2.2));
        view_pager.setClickFlag("1");
        view_pager.start(this, 4000, dotLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
                R.mipmap.hm_banner_selected, R.mipmap.hm_banner_unselected,
                getImageList(Constant.imageUrls, 4), 2.2f);

        //初始化GridView
        adapter = new ImageGridAdapter(this, null);
        gridview.setAdapter(adapter);

        //初始化晒单评价列表
        LinearLayout linearLayout = new LinearLayout(this);
        for (int i = 0; i < 10; i++) {
            View view = View.inflate(this, R.layout.item_shaidan, null);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            NoScrollGridView gridView = (NoScrollGridView) view.findViewById(R.id.gridview);
            gridView.setAdapter(adapter);
            linearLayout.addView(view);
        }
        ysnowscrollview.addView(linearLayout);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_add_shopcar:
                NewToast.show(this, "加入成功", NewToast.LENGTH_LONG);
                i = ZLZQApplication.getInstance().getSp().getIntValue(Constant.ADDNUM);
                i+=1;
                ZLZQApplication.getInstance().getSp().putIntValue(Constant.ADDNUM, i);
                break;
            case R.id.tv_rend_now:
                gotoOtherActivity(ShopCarActivity.class);
                break;
            case R.id.ll_collect:
                if (isCollected) {
                    iv_collect.setVisibility(View.GONE);
                    isCollected = false;
                } else {
                    iv_collect.setVisibility(View.VISIBLE);
                    isCollected = true;
                }
                break;
            case R.id.rl_rend_date:
                list.clear();
                list.add("1个月");
                list.add("2个月");
                list.add("3个月");
                list.add("4个月");
                list.add("5个月");
                list.add("6个月");
                WheelDialog.getInstance().ChossDateOrNumDlg(this, "租赁期", tv_rend_date, list, new WheelDialog.GetCityIdListener() {
                    @Override
                    public void GetCityId(String provinceId, String cityId) {

                    }
                });
                break;
            case R.id.rl_guige:

                break;
            case R.id.rl_num:
                list.clear();
                List<String> num = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
                list.addAll(num);
                WheelDialog.getInstance().ChossDateOrNumDlg(this, "数量", tv_num, list, new WheelDialog.GetCityIdListener() {
                    @Override
                    public void GetCityId(String provinceId, String cityId) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
