package com.bm.zlzq.utils;

import android.content.Context;
import android.widget.Toast;

public class ErrorUtils {

    public static void showErrorMsg(Context context, String error) {
        String errorMsg = "";
        switch (error) {
            case "error_01":
                errorMsg = "参数不正确";
                break;
            case "error_02":
                errorMsg = "服务器异常";
                break;
            case "error_03":
                errorMsg = "手机号已存在";
                break;
            case "error_04":
                errorMsg = "用户名或密码错误";
                break;
            case "error_05":
                errorMsg = "用户被禁用";
                break;
            case "error_06":
                errorMsg = "已经收藏过";
                break;
            case "error_07":
                errorMsg = "没有收藏过";
                break;
            case "error_08":
                errorMsg = "没有数据";
                break;
            case "error_09":
                errorMsg = "商品失效";
                break;
            case "error_10":
                errorMsg = "订单不存在";
                break;
            case "error_11":
                errorMsg = "已经晒单过";
                break;
            case "error_12":
                errorMsg = "订单详情不属于该用户";
                break;
            case "error_13":
                errorMsg = "超级管理员和系统管理员不允许登录";
                break;
            case "error_14":
                errorMsg = "收获地址不存在";
                break;
            case "error_15":
                errorMsg = "卡号或卡密错误";
                break;
            case "error_16":
                errorMsg = "该充值卡已经使用过";
                break;
            case "error_17":
                errorMsg = "旧密码错误";
                break;
            case "error_18":
                errorMsg = "用户不存在";
                break;
            case "error_19":
                errorMsg = "新密码和旧密码不能一致";
                break;
            case "error_20":
                errorMsg = "没有数据";
                break;
            case "error_21":
                errorMsg = "用户余额不足";
                break;
            default:
                break;
        }

        if(error.equals("404"))
        {
            errorMsg = "Network error";
        }
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();

    }
}
