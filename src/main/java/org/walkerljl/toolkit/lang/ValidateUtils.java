/*
 * Copyright (c) 2013 lijunlin All rights reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.lang;

import java.util.regex.Pattern;

/**
 * 验证工具
 * @author lijunlin
 * @since 2011-2-15
 */
public class ValidateUtils {

    private static final String EMAIL  = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String IP
                                       = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,"
            + "2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
    private static final String MOBILE = "^((13)|(15)|(18))\\d{9}$";

    public static boolean validateEmail(String email) {
        return validate(EMAIL, email);
    }

    public static boolean validateIP(String ip) {
        return validate(IP, ip);
    }

    public static boolean validateNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateMobile(String phoneNum) {
        return validate(MOBILE, phoneNum);
    }

    /**
     * 正则验证
     * @param regex
     * @param input
     * @return
     */
    public static boolean validate(String regex, String input) {
        try {
            return Pattern.compile(regex).matcher(input).matches();
        } catch (Exception e) {
            return false;
        }
    }
}