package com.bm.zlzq.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangwm on 2016/1/4.
 */
public class ShaidanListBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String id;
    public String usersId;
    public String head;
    public String nickname;
    public String content;
    public List<ImageBean> comList;
}
