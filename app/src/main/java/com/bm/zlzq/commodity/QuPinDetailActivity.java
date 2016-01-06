package com.bm.zlzq.commodity;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.GuigeBean;
import com.bm.zlzq.bean.ProductBean;
import com.bm.zlzq.bean.ShaidanListBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.shopcar.ShopCarActivity;
import com.bm.zlzq.utils.DisplayUtil;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.view.AutomaticViewPager;
import com.bm.zlzq.view.NoScrollGridView;
import com.bm.zlzq.view.SlidingMenu;
import com.bm.zlzq.view.WheelDialog;
import com.bm.zlzq.view.YsnowScrollView;
import com.bm.zlzq.view.YsnowWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情
 * Created by wangwm on 2015/12/4.
 */
public class QuPinDetailActivity extends BaseActivity implements APICallback.OnResposeListener {
    private AutomaticViewPager view_pager;
    private LinearLayout dotLayout, ll_collect, ll_detail_price_old, ll_shaidan;
    private NoScrollGridView gridview;
    private ImageGridAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private List<String> numlist = new ArrayList<>();
    private RelativeLayout rl_rend_date, rl_guige, rl_num;
    private TextView tv_add_shopcar, tv_rend_now, tv_rend_date, tv_guige, tv_num,
            tv_detail_price, tv_detail_price_old, tv_detail_yajin, tv_all_shaidan,
            tv_product_name, tv_name, tv_content_shaidan, tv_no_shaidan;
    private View v_rend_line, view_one, view_two;
    private ImageView iv_collect;
    private boolean isCollected;
    private RadioGroup radiogroup;
    private RadioButton rb_one, rb_two;
    private SlidingMenu sliding_menu;
    private YsnowWebView ysnowwebview;
    private YsnowScrollView ysnowscrollview;
    private ProductBean product = new ProductBean();
    private String[] rendDate = new String[]{};
    private List<GuigeBean> guigeList = new ArrayList<>();
    private List<ShaidanListBean> shaidanList = new ArrayList<>();
    private int i = 0, number = 0, pageNum = 1, pageSize = 1000, collectflag = -1;
    //    private YsnowListView ysnowslistview;
//    private ShaiDanAdapter shaiDanAdapter;
    private int flag = 0;// 0买  1租
    private String productId = "", guige = "", productDetailId = "", count = "", lease = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_qp_detail);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        productId = getIntent().getStringExtra(Constant.ID);
        initTitle("商品详情");
        initView();
        initData();
        radiogroup.check(R.id.rb_one);
    }

    private void initData() {
        if (flag == 1) {
            tv_detail_yajin.setVisibility(View.VISIBLE);
            ll_detail_price_old.setVisibility(View.GONE);
            rl_rend_date.setVisibility(View.VISIBLE);
        } else {
            tv_detail_yajin.setVisibility(View.GONE);
            ll_detail_price_old.setVisibility(View.VISIBLE);
            tv_detail_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            rl_rend_date.setVisibility(View.GONE);
            v_rend_line.setVisibility(View.GONE);
        }
        ProgressUtils.showProgressDialog("", this);
        WebServiceAPI.getInstance().productInfo(productId, QuPinDetailActivity.this, QuPinDetailActivity.this);
        WebServiceAPI.getInstance().rendDate(productId, QuPinDetailActivity.this, QuPinDetailActivity.this);
        WebServiceAPI.getInstance().Guige(productId, QuPinDetailActivity.this, QuPinDetailActivity.this);
        WebServiceAPI.getInstance().shaiDanList(productId, pageNum, pageSize, QuPinDetailActivity.this, QuPinDetailActivity.this);
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

        //商品名
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);

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
        tv_guige = (TextView) findViewById(R.id.tv_guige);
        //数量
        rl_num = (RelativeLayout) findViewById(R.id.rl_num);
        tv_num = (TextView) findViewById(R.id.tv_num);

        //晒单区
        tv_no_shaidan = (TextView) findViewById(R.id.tv_no_shaidan);
        ll_shaidan = (LinearLayout) findViewById(R.id.ll_shaidan);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_content_shaidan = (TextView) findViewById(R.id.tv_content_shaidan);
        gridview = (NoScrollGridView) findViewById(R.id.gridview);

        //查看全部晒单
        tv_all_shaidan = (TextView) findViewById(R.id.tv_all_shaidan);

        //TabHost
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);


        ll_collect.setOnClickListener(this);
        rl_rend_date.setOnClickListener(this);
        rl_guige.setOnClickListener(this);
        rl_num.setOnClickListener(this);
        tv_add_shopcar.setOnClickListener(this);
        tv_rend_now.setOnClickListener(this);
        tv_all_shaidan.setOnClickListener(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        view_one.setVisibility(View.VISIBLE);
                        view_two.setVisibility(View.GONE);
                        ysnowwebview.setVisibility(View.VISIBLE);
                        ysnowscrollview.setVisibility(View.GONE);
//                        flag = 0;
                        break;
                    case R.id.rb_two:
                        view_one.setVisibility(View.GONE);
                        view_two.setVisibility(View.VISIBLE);
                        ysnowwebview.setVisibility(View.GONE);
                        ysnowscrollview.setVisibility(View.VISIBLE);
//                        flag = 1;
                        break;
                    default:
                        break;
                }
            }
        });

        //初始化轮播图
        int width = DisplayUtil.getWidth(this);
        DisplayUtil.setLayoutParams(view_pager, width, (int) (width / 2.2));
        view_pager.setClickFlag("0");
//        view_pager.start(this, 4000, dotLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
//                R.mipmap.hm_banner_selected, R.mipmap.hm_banner_unselected,
//                getImageList(Constant.imageUrls, 4), 2.2f);

        //初始化GridView
//        adapter = new ImageGridAdapter(this, null);
//        gridview.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_add_shopcar:
                if (TextUtils.isEmpty(productDetailId)) {
                    NewToast.show(this, "请选择商品规格", NewToast.LENGTH_LONG);
                } else if (TextUtils.isEmpty(count)) {
                    NewToast.show(this, "请选择商品数量", NewToast.LENGTH_LONG);
                } else {
                    if (flag == 1) {
                        if (TextUtils.isEmpty(lease)) {
                            NewToast.show(this, "请选择租赁期", NewToast.LENGTH_LONG);
                        } else {
                            WebServiceAPI.getInstance().addShopcar(productDetailId, count, lease, QuPinDetailActivity.this, QuPinDetailActivity.this);
                        }
                    } else {
                        WebServiceAPI.getInstance().addShopcar(productDetailId, count, lease, QuPinDetailActivity.this, QuPinDetailActivity.this);
                    }
                }
                break;
            case R.id.tv_rend_now:
                gotoOtherActivity(ShopCarActivity.class);
                break;
            case R.id.ll_collect:
                if (isCollected) {
                    collectflag = 1;
                    WebServiceAPI.getInstance().collection(productId, "0", "1", QuPinDetailActivity.this, QuPinDetailActivity.this);
                    isCollected = false;
                } else {
                    collectflag = 0;
                    WebServiceAPI.getInstance().collection(productId, "0", "0", QuPinDetailActivity.this, QuPinDetailActivity.this);
                    isCollected = true;
                }
                break;
            case R.id.rl_rend_date:
                if (rendDate.length > 0) {
                    list.clear();
                    for (int i = 0; i < rendDate.length; i++) {
                        list.add(rendDate[i] + "个月");
                    }
                    WheelDialog.getInstance().ChossDateOrNumDlg(this, "租赁期", tv_rend_date, list, new WheelDialog.GetCityIdListener() {
                        @Override
                        public void GetCityId(String provinceId, String cityId, String areaId) {
                            lease = provinceId.substring(0, provinceId.length() - 2);
                        }
                    });
                }
                break;
            case R.id.rl_guige:
                if (guigeList.size() > 0) {
                    WheelDialog.getInstance().SelectGuigeDialog(this, tv_guige, guigeList, flag, new WheelDialog.GetCityIdListener() {
                        @Override
                        public void GetCityId(String provinceId, String cityId, String areaId) {
                            tv_num.setText("");
                            guige = provinceId;
                            for (int i = 0; i < guigeList.size(); i++) {
                                if (guigeList.get(i).name.equals(guige)) {
                                    number = Integer.parseInt(guigeList.get(i).stock);
                                    productDetailId = guigeList.get(i).id;
                                }
                            }
                            String[] numbers = new String[number];
                            for (int i = 0; i < number; i++) {
                                numbers[i] = String.valueOf(i + 1);
                            }
                            numlist.clear();
                            for (int i = 0; i < numbers.length; i++) {
                                numlist.add(numbers[i]);
                            }
                        }
                    });
                }
                break;
            case R.id.rl_num:
                if (numlist.size() > 0) {
                    WheelDialog.getInstance().ChossDateOrNumDlg(this, "数量", tv_num, numlist, new WheelDialog.GetCityIdListener() {
                        @Override
                        public void GetCityId(String provinceId, String cityId, String areaId) {
                            count = provinceId;
                        }
                    });
                } else {
                    NewToast.show(this, "请选择规格", NewToast.LENGTH_LONG);
                }
                break;
            case R.id.tv_all_shaidan:
                sliding_menu.openMenu();
                radiogroup.check(R.id.rb_two);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            ProgressUtils.cancleProgressDialog();
            switch (tag) {
                case 0:
                    product = apiResponse.data.product;

                    view_pager.start(this, 4000, dotLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
                            R.mipmap.hm_banner_selected, R.mipmap.hm_banner_unselected,
                            getImageBean(product.picList, product.picList.size()), 2.2f);

                    tv_product_name.setText(product.name);
                    if (flag == 1) {
                        tv_detail_price.setText("租赁单价:" + product.Price + "元/月");
                    } else {
                        tv_detail_price.setText("购买价格:" + product.Price + "-" + product.maxPrice);
                    }
                    tv_detail_yajin.setText("押金:" + product.oldPrice + "元");
                    tv_detail_price_old.setText(product.oldPrice + "-" + product.maxOldPrice);
                    if (!TextUtils.isEmpty(product.nickname)) {
                        tv_no_shaidan.setVisibility(View.GONE);
                        ll_shaidan.setVisibility(View.VISIBLE);
                        tv_name.setText(product.nickname);
                        tv_content_shaidan.setText(product.content);
                        adapter = new ImageGridAdapter(this, product.comList);
                        gridview.setAdapter(adapter);
                    } else {
                        tv_no_shaidan.setVisibility(View.VISIBLE);
                        ll_shaidan.setVisibility(View.GONE);
                    }
                    if (product.iscollect.equals("0")) {
                        iv_collect.setVisibility(View.GONE);
                        isCollected = false;
                    } else {
                        iv_collect.setVisibility(View.VISIBLE);
                        isCollected = true;
                    }
                    break;
                case 1:
                    rendDate = apiResponse.data.lease.split(",");
                    break;
                case 2:
                    guigeList.clear();
                    guigeList.addAll(apiResponse.data.list);
                    break;
                case 3:
                    shaidanList.clear();
                    shaidanList.addAll(apiResponse.data.list);
                    //晒单评价列表
                    LinearLayout linearLayout = new LinearLayout(this);
                    for (int i = 0; i < shaidanList.size(); i++) {
                        View view = View.inflate(this, R.layout.item_shaidan, null);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        TextView tv_item_name = (TextView) view.findViewById(R.id.tv_item_name);
                        TextView tv_item_content_shaidan = (TextView) view.findViewById(R.id.tv_item_content_shaidan);
                        NoScrollGridView gridView = (NoScrollGridView) view.findViewById(R.id.gridview);

                        tv_item_name.setText(shaidanList.get(i).nickname);
                        tv_item_content_shaidan.setText(shaidanList.get(i).content);
                        adapter = new ImageGridAdapter(this, shaidanList.get(i).comList);
                        gridView.setAdapter(adapter);
                        linearLayout.addView(view);
                    }
                    ysnowscrollview.addView(linearLayout);
                    break;
                case 4:
                    if (collectflag == 0) {
                        NewToast.show(this, "收藏成功", NewToast.LENGTH_LONG);
                        iv_collect.setVisibility(View.VISIBLE);
                    } else if (collectflag == 1) {
                        NewToast.show(this, "收藏已取消", NewToast.LENGTH_LONG);
                        iv_collect.setVisibility(View.GONE);
                    }
                    break;
                case 5:
                    NewToast.show(this, "加入成功", NewToast.LENGTH_LONG);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
