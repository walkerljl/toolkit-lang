/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.lang;

/**
 * Long型比较,防止引用类型报错
 * @author lijunlin
 * @since 2014-2-13
 */
public final class LongUtils {

    private static Long ZERO = new Long(0);

    private LongUtils() {}

    public static boolean equals(final Long num1, final Long num2) {
        if (num1 != null && num2 != null && num1.longValue() == num2.longValue()) {
            return true;
        }
        return false;
    }

    public static boolean notEquals(final Long num1, final Long num2) {
        return !equals(num1, num2);
    }

    public static boolean greatThan(final Long num1, final Long num2) {
        if (num1 != null && num2 != null && num1.longValue() > num2.longValue()) {
            return true;
        }
        return false;
    }

    public static boolean greatThanZero(final Long num1) {
        if (num1 != null && num1.longValue() > ZERO.longValue()) {
            return true;
        }
        return false;
    }

    public static boolean greatThanEquals(final Long num1, final Long num2) {
        if (num1 != null && num2 != null && num1.longValue() >= num2.longValue()) {
            return true;
        }
        return false;
    }

    public static boolean lessThan(final Long num1, final Long num2) {
        if (num1 != null && num2 != null && num1.longValue() < num2.longValue()) {
            return true;
        }
        return false;
    }

    public static boolean lessThanEquals(final Long num1, final Long num2) {
        if (num1 != null && num2 != null && num1.longValue() <= num2.longValue()) {
            return true;
        }
        return false;
    }

    public static long intValue(final Long num) {
        if (num == null) {
            return 0;
        }
        return num.longValue();
    }

    public static Long parseLong(final String num) {
        try {
            return Long.parseLong(num);
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Long parseLong(final Object num) {
        try {
            return Long.parseLong(num.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Long[] split(String str, String separatorChars) {
        String[] items = StringUtils.split(str, separatorChars);
        if (items != null && items.length > 0) {
            Long[] arr = new Long[items.length];
            for (int i = 0; i < items.length; i++) {
                arr[i] = parseLong(items[i]);
            }
            return arr;
        }
        return null;
    }
}