package org.walkerljl.toolkit.lang;

/**
 *
 * NumUtils
 *
 * @author lijunlin
 */
public class NumUtils {

    public static boolean greatThan(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() > num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() > num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() > num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() > num2.intValue();
        }
        return false;
    }

    public static boolean lessThan(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() < num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() < num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() < num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() < num2.intValue();
        }
        return false;
    }

    public static boolean equals(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        if (num1 instanceof Long && num2 instanceof Long) {
            return num1.longValue() == num2.longValue();
        } else if (num1 instanceof Long && num2 instanceof Integer) {
            return num1.longValue() == num2.intValue();
        } else if (num1 instanceof Integer && num2 instanceof Long) {
            return num1.intValue() == num2.longValue();
        } else if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() == num2.intValue();
        }
        return false;
    }

    public static boolean notEquals(Number num1, Number num2) {
        return !equals(num1, num2);
    }

    // 判断每个都必须大于第一个数,且不为NULL
    public static boolean greatThanFirstEvery(Number num, Number... nums) {
        boolean result = false;
        if (num != null && nums != null && nums.length > 0) {
            for (Number n : nums) {
                result = greatThan(n, num);
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }

    public static int intValue(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static long longValue(Long num) {
        if (num == null) {
            return 0;
        }
        return num.longValue();
    }

    public static int parseInt(Object num) {
        try {
            if (num != null && num instanceof Boolean) {
                return ((Boolean) num).booleanValue() ? 1 : 0;
            }
            return Integer.parseInt(num.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static long parseLong(Object num) {
        try {
            return Long.parseLong(num.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static float parseFloat(Object num) {
        try {
            return Float.parseFloat(num.toString());
        } catch (Exception e) {
            return 0f;
        }
    }

    public static long defaultValue(Number value, long defaultValue) {
        if (value != null) {
            return value.longValue();
        }
        return defaultValue;
    }

    public static int defaultValue(Number value, int defaultValue) {
        if (value != null) {
            return value.intValue();
        }
        return defaultValue;
    }
}