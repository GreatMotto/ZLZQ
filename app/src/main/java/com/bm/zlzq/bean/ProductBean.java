package com.bm.zlzq.bean;

import java.io.Serializable;
import java.util.List;

public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String id;
    public String path;
    public String name;
    public String Price;
    public String count;
    public String oldPrice;
    public String maxPrice;
    public String maxOldPrice;
    public String deposit;
    public String description;
    public String iscollect;
    public String nickname;
    public String content;
    public String status;
    public List<ImageBean> picList ;
    public List<ImageBean> comList ;

}
