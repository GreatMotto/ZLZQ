package com.bm.zlzq;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/29.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp_guide;
    private TextView tv_experience;
    private SharedPreferencesHelper sp;
    private List<ImageView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_guide);
        sp = ZLZQApplication.getInstance().getSp();
        initView();
    }

    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        tv_experience = (TextView) findViewById(R.id.tv_experience);

        int[] imageIDs = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3,
                R.mipmap.guide_4, R.mipmap.guide_5};

        ImageView iv;
        for (int i = 0; i < imageIDs.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageIDs[i]);
            list.add(iv);
        }
        vp_guide.setAdapter(new GuideAdapter());
        vp_guide.setOnPageChangeListener(this);
        tv_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.putBooleanValue(Constant.ISOPENMAIN, true);
                gotoOtherActivity(LoginActivity.class);
                finish();
            }
        });
    }

    public class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = list.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (position == list.size() - 1){
            tv_experience.setVisibility(View.VISIBLE);
        }else {
            tv_experience.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
