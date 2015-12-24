package com.bm.zlzq.bean;

import java.io.Serializable;

/**
 * Created by wangwm on 2015/12/23.
 */
public class CouponBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String enddate;
    public String fullprice;
    public String minusprice;
    public String isget;// 是否已获取  0-已获取  1-未获取
}
