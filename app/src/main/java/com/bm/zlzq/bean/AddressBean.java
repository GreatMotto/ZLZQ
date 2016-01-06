package com.bm.zlzq.bean;

import java.io.Serializable;

/**
 * Created by wangwm on 2015/12/21.
 */
public class AddressBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public String consignee;//收货人
    public String mobile;//联系电话
    public String provincesId;//省份id
    public String citysId;//城市id
    public String areasId;//区域id
    public String area;//省市区
    public String id;//收货地址id
    public String street;//街道
    public String address;//详细地址
    public String status;// 0-非默认  1-默认地址
}
