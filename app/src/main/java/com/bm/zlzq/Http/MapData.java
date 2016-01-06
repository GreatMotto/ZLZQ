package com.bm.zlzq.Http;

import com.bm.zlzq.bean.ActivityBean;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.bean.AddressBean;
import com.bm.zlzq.bean.Page;
import com.bm.zlzq.bean.ProductBean;
import com.bm.zlzq.bean.UsersBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nathan on 15/5/22.
 */
public class MapData<T> implements Serializable {
    /**
     * MapData封装所有数据类型
     */
    public List<T> list;

    public Page page;

    public String id;

    public ActivityBean activity;

    public ProductBean product;

    public String lease;

    public String content;

    public UsersBean users;

    public MerchantBean merchant;

    public AddressBean addressBean;

}

