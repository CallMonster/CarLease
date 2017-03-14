package com.tj.pxdl.carlease.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by v1ncent on 2016/8/4.
 * 校验工具类
 */
public class CheckUtil {
    public static final String MOBILE =
            "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";//手机号正则

    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     *
     * @param str     匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static boolean Regular(String str, String pattern) {
        if (TextUtils.isEmpty(str))
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否输入的是手机号
     *
     * @return
     */
    public static boolean strIsMobile(String mobile) {
        return Regular(mobile, MOBILE);
    }

    /**
     * 验证是否符合密码
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,32}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
