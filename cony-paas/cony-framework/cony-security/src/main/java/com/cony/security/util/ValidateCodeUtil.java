package com.cony.security.util;


import com.cony.security.cache.PhoneCache;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by wangk-p on 2017/11/30.
 */
public class ValidateCodeUtil {

    /**
     * 图片验证码判断
     * @param request
     * @return
     */
    public static boolean checkPicCode(String picCode,HttpServletRequest request) {
        String result_picCode = request.getSession().getAttribute("picCode")
                .toString(); // 获取存于session的验证值
        request.getSession().setAttribute("picCode", null);
        if (null == picCode || !result_picCode.equalsIgnoreCase(picCode)) {
            return false;
        }
        return true;
    }

    /**
     * 手机验证码判断
     * @param username
     * @param msgCode
     * @return
     */
    public static boolean checkMsgCode(String username,String msgCode,PhoneCache phoneCache) {
        String result_msgCode = phoneCache.getMsgCodeByUsername(username); // 获取存于cache的验证值
        if (null == msgCode || !result_msgCode.equalsIgnoreCase(msgCode)) {
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$");
        return  pattern.matcher(phoneNumber).find();
    }

}
