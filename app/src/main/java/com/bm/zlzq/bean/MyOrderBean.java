package com.bm.zlzq.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyOrderBean implements Serializable{
    private static final long serialVersionUID = 1L;

    public String ordernumber;
    public String state;
    public List<ShopCarBean> goodslist = new ArrayList<>();
    public String blkbtntext;
    public String orgbtntext;

}
