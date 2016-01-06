package com.bm.zlzq.constant;

/**
 * Created by zhaoxin on 2015/7/14.
 */
public class Constant {

    public final static String CACHE_JSON_DATA_PATH = "/zlzq/json/";// 缓存json格式数据的路径
    public final static String CACHE_IMAGE_PATH = "/zlzq/cache";// 缓存图片的路径
    public final static String CRASH_ERROR_FILE_PATH = "/zlzq/crash/";// 保存报错信息文件的路径
    public final static String CRASH_PIC_PATH = "/zlzq/pic/";// 保存上传图片的路径
    public final static String UPLOAD_PICTURE_PATH = "/zlzq/upload/";// 保存着上传图片的路径

    // 上次登录用户名SHAREDPREFERENCES
    public static final String SP_NAME = "name";// sp文件名
    public static final String ISLOGIN = "islogin";//
    public static final String ISOPENMAIN = "isopenmain";// 是否打开过
    public static final String FLAG = "flag";// 跳转键
    public static final String TAG = "tag";// 跳转标记
    public static final String ID = "id";// ID
    public static final String LONTITUDE = "lontitude";// 经度
    public static final String LATITUDE = "latitude";// 纬度
    public static final String USERID = "userid";// 用户ID
    public static final String PHONE = "phone";//登录手机号
    public static final String HISTORY = "history";//搜索历史
    public static final String ORDERNUMBER = "ordernumber";//订单号
    public static final String LIST = "list";//商品列表
    public static final String PRICE = "price";//总计价格
    public static final String TITLE = "title";//商户名
    public static final String EMAIL = "email";//email
    public static final String PASSWORD = "password";//password

    // 验证手机号码正则表达式
    public static final String CheckMobile = "^((1[358][0-9])|(14[57])|(17[0678]))\\d{8}$";

    // 邮箱验证
    public static final String TESTEMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    //首页banner图
    public static final String[] imageUrls = {
            "http://pic32.nipic.com/20130809/13364285_114218562121_2.jpg",
            "http://pic31.nipic.com/20130713/8952533_132447454000_2.jpg",
            "http://pic26.nipic.com/20121220/9422601_171407746000_2.jpg",
            "http://pic8.nipic.com/20100804/2387335_222239605083_2.jpg"};
}
