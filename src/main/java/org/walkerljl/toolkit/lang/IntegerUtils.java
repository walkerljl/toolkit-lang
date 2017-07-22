/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.lang;

/**
 * 整型比较,防止引用类型报错
 * @author lijunlin
 * @since 2014-2-13
 */
public final class IntegerUtils {

    private IntegerUtils() {}

    public static boolean equals(Integer num1, Integer num2) {
        return num1 != null && num2 != null && num1.intValue() == num2.intValue();
    }

    public static boolean notEquals(Integer num1, Integer num2) {
        return !IntegerUtils.equals(num1, num2);
    }

    public static boolean greatThan(Integer num1, Integer num2) {
        return num1 != null && num2 != null && num1.intValue() > num2.intValue();
    }

    public static boolean greatThanEquals(Integer num1, Integer num2) {
        return num1 != null && num2 != null && num1.intValue() >= num2.intValue();
    }

    public static boolean lessThan(Integer num1, Integer num2) {
        return num1 != null && num2 != null && num1.intValue() < num2.intValue();
    }

    public static boolean lessThanEquals(Integer num1, Integer num2) {
        return num1 != null && num2 != null && num1.intValue() <= num2.intValue();
    }

    public static int intValue(Integer num) {
        return num == null ? 0 : num.intValue();
    }

    public static int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer parseInt(Object num) {
        try {
            return Integer.parseInt(num.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static Long parseLong(Object num) {
        try {
            return Long.parseLong(num.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Integer[] split(String str, String separatorChars) {
        String[] items = StringUtils.split(str, separatorChars);
        if (items != null && items.length > 0) {
            Integer[] arr = new Integer[items.length];
            for (int i = 0; i < items.length; i++) {
                arr[i] = parseInt(items[i]);
            }
            return arr;
        }
        return null;
    }
}