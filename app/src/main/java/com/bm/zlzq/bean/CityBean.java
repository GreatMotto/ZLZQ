package com.bm.zlzq.bean;

import java.io.Serializable;
import java.util.List;

public class CityBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String name;
    public String id;
    public List<AreaBean> areaList;

}
