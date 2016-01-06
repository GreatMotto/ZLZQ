package com.bm.zlzq.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangwm on 2015/12/30.
 */
public class MessageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String createTime; //时间
    public List<Content> contentList; //消息内容

}
