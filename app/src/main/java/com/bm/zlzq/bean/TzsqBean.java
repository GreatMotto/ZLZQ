package com.bm.zlzq.bean;

import java.io.Serializable;

public class TzsqBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public boolean ischeck;//状态
    public String number;//数量
    public String orderNumber;//订单号
    public String name;//商品名称
    public String price;//单价
    public String prices;//单个订单价
    public String totalPrice;//总价
    public String standard;//规格
    public String times;//租期
    public String express;//送货方式


    public TzsqBean(String express, String id, boolean ischeck, String name, String number, String orderNumber, String price, String prices, String standard, String times, String totalPrice) {
        this.express = express;
        this.id = id;
        this.ischeck = ischeck;
        this.name = name;
        this.number = number;
        this.orderNumber = orderNumber;
        this.price = price;
        this.prices = prices;
        this.standard = standard;
        this.times = times;
        this.totalPrice = totalPrice;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}

