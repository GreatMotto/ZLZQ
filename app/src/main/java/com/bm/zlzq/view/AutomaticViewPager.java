package com.bm.zlzq.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.ImageBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.discount.ActivitiesActivity;
import com.bm.zlzq.utils.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 广告模块图片滚动
 */
public class AutomaticViewPager extends ViewPager {

    Activity mActivity; // 上下文
    // List<NetworkImageView> mListViews; // 图片组
    int mScrollTime = 0;
    Timer timer;
    int oldIndex = 0;
    int curIndex = 0;
    private List<ImageBean> list;
    PointF downP = new PointF();
    PointF curP = new PointF();
    private MyPagerAdapter adapter = new MyPagerAdapter();
    float radio;
    private String clickFlag="";//轮播图点击标记，空：默认轮播图，"0"：缩放,"1"：进入详情
    private String scaleFlag="";//缩放标记

    public AutomaticViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 开始广告滚动
     *
     * @param mainActivity     显示广告的主界面
     * @param scrollTime       滚动间隔 ,0为不滚动
     * @param ovalLayout       圆点容器,可为空,LinearLayout类型
     * @param ovalLayoutId     ovalLayout为空时 写0, 圆点layout XMl
     * @param ovalLayoutItemId ovalLayout为空时 写0,圆点layout XMl 圆点XMl下View ID
     * @param focusedId        ovalLayout为空时 写0, 圆点layout XMl 选中时的动画
     * @param normalId         ovalLayout为空时 写0, 圆点layout XMl 正常时背景
     */
    public void start(Activity mainActivity, int scrollTime, LinearLayout ovalLayout,
                      int ovalLayoutId, int ovalLayoutItemId, int focusedId, int normalId,
                      List<ImageBean> list, float radio) {

        // if (list == null) {
        mActivity = mainActivity;
        // mListViews = imgList;
        mScrollTime = scrollTime;
        this.list = list;
        this.radio = radio;
        // 设置圆点
        setOvalLayout(ovalLayout, ovalLayoutId, ovalLayoutItemId, focusedId, normalId);
        this.setAdapter(adapter);// 设置适配器
        //设置ViewPager的默认项, 设置为总数的1000倍，实现可以往右滑动的假象
        int count = (this.list!=null&&this.list.size()>1)?this.list.size():0;
        if(count>1) {
            this.setCurrentItem(count * 1000);
        }

        if (scrollTime != 0 && list.size() > 1) {
            // 设置滑动动画时间 ,如果用默认动画时间可不用 ,反射技术实现
            new FixedSpeedScroller(mActivity).setDuration(this, 1000);

            startTimer();
            // 触摸时停止滚动
            this.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_CANCEL:
                            startTimer();
                            break;
                        case MotionEvent.ACTION_UP:
                            startTimer();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            stopTimer();
                            if (downP.x == curP.x && downP.y == curP.y) {
                                return false;
                            }
                            break;
                    }
//                    if (event.getAction() == MotionEvent.ACTION_UP) {
//                        startTimer();
//                    } else {
//                        stopTimer();
//                        ((ViewGroup) v).requestDisallowInterceptTouchEvent(false);
//                        if (downP.x == curP.x && downP.y == curP.y) {
//                            return false;
//                        }
//                    }
                    return false;
                }
            });
        }
        if (list.size() > 1) {
            //this.setCurrentItem(0);// 设置选中为中间/图片为和第0张一样
        }
    }

    public void setClickFlag(String clickFlag)
    {
        this.clickFlag=clickFlag;
    }

    public void setscaleFlag(String scaleFlag)
    {
        this.scaleFlag=clickFlag;
    }

    // els  // list.clear();
    // // mListViews = imgList;
    // this.list = list;
    // removeAllViews();
    // adapter.notifyDataSetChanged();
    // }
    // }

    // 设置圆点
    private void setOvalLayout(final LinearLayout ovalLayout, int ovalLayoutId,
                               final int ovalLayoutItemId, final int focusedId, final int normalId) {
        if (ovalLayout != null) {
            ovalLayout.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            if (list.size() <= 1)//当前图片只有1张时不显示圆点
            {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                ovalLayout.addView(inflater.inflate(ovalLayoutId, null));

            }
            // 选中第一个
            ovalLayout.getChildAt(0).findViewById(ovalLayoutItemId)
                    .setBackgroundResource(focusedId);
            this.setOnPageChangeListener(new OnPageChangeListener() {

                @Override
                public void onPageSelected(int i) {
                    curIndex = i % list.size();
                    // 取消圆点选中
                    ovalLayout.getChildAt(oldIndex).findViewById(ovalLayoutItemId)
                            .setBackgroundResource(normalId);
                    // 圆点选中
                    ovalLayout.getChildAt(curIndex).findViewById(ovalLayoutItemId)
                            .setBackgroundResource(focusedId);
                    oldIndex = curIndex;
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                }
            });
        }
    }

    /**
     * 取得当明选中下标
     *
     * @return
     */
    public int getCurIndex() {
        return curIndex;
    }

    /**
     * 停止滚动
     */
    public void stopTimer() {
        Log.e("stopTimer", "stopTimer");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 开始滚动
     */
    public void startTimer() {
        Log.e("startTimer", "startTimer");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        AutomaticViewPager.this.setCurrentItem(AutomaticViewPager.this
                                .getCurrentItem() + 1);
                    }
                });
            }
        }, mScrollTime, mScrollTime);
    }

    // 适配器 //循环设置
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (list.size() == 1) {// 一张图片时不用流动
                return list.size();
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // TODO Auto-generated method stub
            View convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, null);

            ImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            if (list.size() != 0) {
                float ratio=0,widthI=0,heightI=0;
                try{
                    String width=  list.get(position % list.size()).width;
                    String height=  list.get(position % list.size()).height;
                    widthI= Integer.parseInt(width);
                    heightI= Integer.parseInt(height);
                    ratio = widthI/heightI;
                }catch (Exception ex){}
                if(widthI>=1000||ratio>2){
                    iv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                else if((widthI<500&&widthI>0)||(ratio>=0.5 &&ratio<=2)){
                    iv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                else{
                    iv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                ImageLoader.getInstance().displayImage(list.get(position % list.size()).path, iv_image, options);

                if("0".equals(clickFlag)) {
                    iv_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewPagerDialog dlg = new ViewPagerDialog(mActivity, list,position % list.size());
                            dlg.showViewPagerDialog();
                        }
                    });
                }
                else if("1".equals(clickFlag)){
                    iv_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 在此处添加跳转逻辑
                            Intent intent = new Intent();
                            intent.setClass(mActivity, ActivitiesActivity.class);
                            intent.putExtra(Constant.FLAG, 0);
                            mActivity.startActivity(intent);
                        }
                    });
                }
            }


//            SimpleDraweeView sdv = ViewHolder.get(convertView, R.id.my_image_view);
//            sdv.setAspectRatio(radio);
//            if(list.size() != 0) {
//                sdv.setImageURI(Uri.parse(list.get(position % list.size()).path));
//            }
            container.addView(convertView);
            return convertView;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
            //((ViewPager)arg0).removeView(mImageViews[position % count]);
        }
    }



}
