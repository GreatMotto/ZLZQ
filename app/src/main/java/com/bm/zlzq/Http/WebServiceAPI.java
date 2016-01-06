package com.bm.zlzq.Http;

import android.content.Context;
import android.webkit.MimeTypeMap;

import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.bean.ActivityBean;

import com.bm.zlzq.bean.AddressBean;

import com.bm.zlzq.bean.GuigeBean;
import com.bm.zlzq.bean.HotBean;

import com.bm.zlzq.bean.ImageBean;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.bean.MerchantListBean;
import com.bm.zlzq.bean.MessageBean;
import com.bm.zlzq.bean.ProductBean;
import com.bm.zlzq.bean.ProductListBean;
import com.bm.zlzq.bean.ProductType;
import com.bm.zlzq.bean.ShaidanListBean;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.bean.UsersBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.Md5Util;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import retrofit.mime.TypedFile;

/**
 * Created by wangwm on 2015/12/30.
 */
public class WebServiceAPI {
    private WebServiceAPI() {
    }

    private static class Datacontroller {
        /**
         * 单例变量
         */
        private static WebServiceAPI instance = new WebServiceAPI();
    }

    public static WebServiceAPI getInstance() {
        return Datacontroller.instance;
    }

    private String getMimeType(File file) {
        String suffix = getSuffix(file);
        if (suffix == null) {
            return "file/*";
        }
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);
        if (type != null || !type.isEmpty()) {
            return type;
        }
        return "file/*";
    }

    private String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String fileName = file.getName();
        if (fileName.equals("") || fileName.endsWith(".")) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase(Locale.US);
        } else {
            return null;
        }
    }

    /**
     * 注册
     *
     * @param mobile     手机号
     * @param password   密码
     * @param inviteCode 邀请码
     */
    public void register(String mobile, String password, String inviteCode, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("password", Md5Util.md5(password));
        map.put("inviteCode", inviteCode);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.REGISTER, map, new APICallback(context, listener, 0));
    }

    /**
     * 登录
     *
     * @param mobile          手机号
     * @param password        密码
     * @param registrationids 推送id
     */
    public void login(String mobile, String password, String registrationids, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("password", Md5Util.md5(password));
        map.put("inviteCode", registrationids);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.LOGIN, map, new APICallback(context, listener, 0));
    }

    /**
     * 首页banner图
     *
     * @param listener
     * @param context
     */
    public void bannerList(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        APIClient.getInstance().getAPIService(ImageBean.class).PostAPI(Urls.BANNERLIST, map, new APICallback(context, listener, 0));
    }

    /**
     * 活动详细
     *
     * @param id
     * @param listener
     * @param context
     */
    public void activityInfo(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        APIClient.getInstance().getAPIService(ActivityBean.class).PostAPI(Urls.ACTIVITYINFO, map, new APICallback(context, listener, 0));
    }

    /**
     * 通知消息
     *
     * @param listener
     * @param context
     */
    public void message(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(MessageBean.class).PostAPI(Urls.MESSAGE, map, new APICallback(context, listener, 0));
    }

    /**
     * 热销/热租商品
     *
     * @param type     0 ：热销，1：热租
     * @param listener
     * @param context
     */
    public void hotProduct(String type, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        APIClient.getInstance().getAPIService(HotBean.class).PostAPI(Urls.HOTPRODUCT, map, new APICallback(context, listener, 1));
    }

    /**
     * 商品列表
     *
     * @param type
     * @param name
     * @param productTypeIds
     * @param minPrice
     * @param maxPrice
     * @param range
     * @param pageNum
     * @param pageSize
     * @param listener
     * @param context
     */
    public void productList(int type, String name, String productTypeIds, String minPrice, String maxPrice, String range, int pageNum, int pageSize, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", String.valueOf(type));
        map.put("name", name);
        map.put("productTypeIds", productTypeIds);
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);
        map.put("range", range);
        map.put("pageNum", String.valueOf(pageNum));
        map.put("pageSize", String.valueOf(pageSize));
        APIClient.getInstance().getAPIService(ProductListBean.class).PostAPI(Urls.PRODUCT, map, new APICallback(context, listener, 0));
    }

    /**
     * 查询商品类别
     *
     * @param listener
     * @param context
     */
    public void productType(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        APIClient.getInstance().getAPIService(ProductType.class).PostAPI(Urls.PRODUCTTYPE, map, new APICallback(context, listener, 1));
    }

    /**
     * 商品详细
     *
     * @param id
     * @param listener
     * @param context
     */
    public void productInfo(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(ProductBean.class).PostAPI(Urls.PRODUCTINFO, map, new APICallback(context, listener, 0));
    }

    /**
     * 查询租赁期
     *
     * @param id
     * @param listener
     * @param context
     */
    public void rendDate(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.RENDDATE, map, new APICallback(context, listener, 1));
    }

    /**
     * 查询规格
     *
     * @param id
     * @param listener
     * @param context
     */
    public void Guige(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        APIClient.getInstance().getAPIService(GuigeBean.class).PostAPI(Urls.GUIGE, map, new APICallback(context, listener, 2));
    }

    /**
     * 查询晒单列表
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param listener
     * @param context
     */
    public void shaiDanList(String id, int pageNum, int pageSize, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pageNum", String.valueOf(pageNum));
        map.put("pageSize", String.valueOf(pageSize));
        APIClient.getInstance().getAPIService(ShaidanListBean.class).PostAPI(Urls.SHAIDANLIST, map, new APICallback(context, listener, 3));
    }

    /**
     * 添加/取消收藏
     *
     * @param objectid
     * @param objecttype
     * @param type
     * @param listener
     * @param context
     */
    public void collection(String objectid, String objecttype, String type, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        map.put("objectid", objectid);
        map.put("objecttype", objecttype);
        map.put("type", type);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.COLLECTION, map, new APICallback(context, listener, 4));
    }

    /**
     * 加入购物车
     *
     * @param productDetailId
     * @param count
     * @param lease
     * @param listener
     * @param context
     */
    public void addShopcar(String productDetailId, String count, String lease, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        map.put("productDetailId", productDetailId);
        map.put("count", count);
        map.put("lease", lease);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.ADDSHOPCAR, map, new APICallback(context, listener, 5));
    }

    /**
     * 查询个人信息
     *
     * @param listener
     * @param context
     */
    public void usersinfo(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.USERSINFO, map, new APICallback(context, listener, 0));
    }

    /**
     * 修改个人资料
     *
     * @param listener
     * @param context
     */
    public void updateInfo(String nickname, String sex, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nickname", nickname);
        map.put("sex", sex);
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.UPDATEINFO, map, new APICallback(context, listener, 0));
    }

    /**
     * 修改个人头像
     *
     * @param listener
     * @param context
     */
    public void updateHead(File fromfile, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        TypedFile file = new TypedFile(getMimeType(fromfile), fromfile);
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.UPDATEHEAD, file, map, new APICallback(context, listener, 1));
    }

    /**
     * 修改密码
     *
     * @param listener
     * @param context
     */
    public void updpassword(String mobile, String password, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("password", Md5Util.md5(password));
//        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.FORGETPASSWORD, map, new APICallback(context, listener, 1));
    }

    /**
     * 新增地址
     *
     * @param listener
     * @param context
     */
    public void addInfo(String consignee, String mobile, String provincesId, String citysId, String areasId, String street, String address, String status, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("consignee", consignee);//收货人
        map.put("mobile", mobile);//手机
        map.put("provincesId", provincesId);//省
        map.put("citysId", citysId);//市
        map.put("areasId", areasId);//区
        map.put("street", street);//街道
        map.put("address", address);//详细地址
        map.put("status", status);//默认地址状态
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(AddressBean.class).PostAPI(Urls.ADDINFO, map, new APICallback(context, listener, 0));
    }

    /**
     * 收货地址
     *
     * @param listener
     * @param context
     */
    public void address(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("status", status);//默认地址状态
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(AddressBean.class).PostAPI(Urls.ADDRESS, map, new APICallback(context, listener, 0));
    }

    /**
     * 删除收货地址
     *
     * @param listener
     * @param context
     */
    public void deladdress(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);//收货地址id
        APIClient.getInstance().getAPIService(AddressBean.class).PostAPI(Urls.DELADDRESS, map, new APICallback(context, listener, 1));
    }

    /**
     * 修改收货地址/默认地址
     *
     * @param listener
     * @param context
     */
    public void updateaddress(String id, String consignee, String mobile, String provincesId, String citysId, String areasId, String street, String address, String status, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);//收货地址id
        map.put("consignee", consignee);//收货人
        map.put("mobile", mobile);//手机
        map.put("provincesId", provincesId);//省
        map.put("citysId", citysId);//市
        map.put("areasId", areasId);//区
        map.put("street", street);//街道
        map.put("address", address);//详细地址
        map.put("status", status);//默认地址状态
        APIClient.getInstance().getAPIService(AddressBean.class).PostAPI(Urls.UPDATEADDRESS, map, new APICallback(context, listener, 2));
    }


    /**
     * 购物车列表
     *
     * @param listener
     * @param context
     */
    public void shopcarList(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(ShopCarBean.class).PostAPI(Urls.SHOPCARLIST, map, new APICallback(context, listener, 0));
    }


    /**
     * 修改手机号
     *
     * @param listener
     * @param context
     */
    public void updatephone(String mobile, String password, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);//
        map.put("password", password);//
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.UPDATEPHONE, map, new APICallback(context, listener, 0));
    }

    /**
     * 修改密码
     *
     * @param listener
     * @param context
     */
    public void updatepassword(String oldpassword, String password, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("oldpassword", Md5Util.md5(oldpassword));//
        map.put("password", Md5Util.md5(password));//
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.UPDATEPASSWORD, map, new APICallback(context, listener, 0));
    }


    /**
     * 删除购物车
     *
     * @param id
     * @param listener
     * @param context
     */
    public void delShopcar(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.DELSHOPCAR, map, new APICallback(context, listener, 1));
    }

    /**
     * 商户列表
     * @param productDetailId
     * @param count
     * @param name
     * @param pageNum
     * @param pageSize
     * @param listener
     * @param context
     */
    public void merchantList(String productDetailId, String count, String name, int pageNum, int pageSize, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("productDetailId", productDetailId);
        map.put("count", count);
//        map.put("lon", ZLZQApplication.getInstance().getSp().getValue(Constant.LONTITUDE));
//        map.put("lat", ZLZQApplication.getInstance().getSp().getValue(Constant.LATITUDE));
        map.put("name", name);
        map.put("pageNum", String.valueOf(pageNum));
        map.put("pageSize", String.valueOf(pageSize));
        APIClient.getInstance().getAPIService(MerchantListBean.class).PostAPI(Urls.MERCHANTLIST, map, new APICallback(context, listener, 0));
    }

    /**
     * 商户详情
     * @param id
     * @param listener
     * @param context
     */
    public void merchantInfo(String id, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        APIClient.getInstance().getAPIService(MerchantBean.class).PostAPI(Urls.MERCHANTINFO, map, new APICallback(context, listener, 1));
    }

    /**
     * 意见反馈
     * @param listener
     * @param context
     */
    public void feedback(String content, APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("content", content);
        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(UsersBean.class).PostAPI(Urls.FEEDBACK, map, new APICallback(context, listener, 0));
    }

    /**
     * 关于我们
     * @param listener
     * @param context
     */
    public void about(APICallback.OnResposeListener listener, Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("usersId", ZLZQApplication.getInstance().getSp().getValue(Constant.USERID));
        APIClient.getInstance().getAPIService(String.class).PostAPI(Urls.ABOUT, map, new APICallback(context, listener, 0));
    }

}
