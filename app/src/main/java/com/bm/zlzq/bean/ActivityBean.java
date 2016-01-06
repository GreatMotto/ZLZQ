package com.bm.zlzq.bean;

import java.io.Serializable;

/**
 * Created by wangwm on 2015/12/30.
 */
public class ActivityBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String id; //活动ID
    public String title; //活动标题
    public String path; //图片路径
    public String description; //活动详细
}
