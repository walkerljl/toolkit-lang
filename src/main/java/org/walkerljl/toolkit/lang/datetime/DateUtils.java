/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * DateUtils
 *
 * @author lijunlin
 */
public class DateUtils {

    /**
     * 获取服务器时间
     * @return
     */
    public static long getServerTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取format格式字符串
     * @param format 格式字符串
     * @return
     */
    public static String getFormatDate(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 根据指定时间单位获取当前日期时间字符串
     * @param timeUnit 时间单位
     * @return
     */
    public static String getFormatDate(TimeUnit timeUnit) {
        String format = "yyyyMMddHHmmss";
        if (timeUnit == TimeUnit.DAYS) {
            format = "yyyyMMdd";
        } else if (timeUnit == TimeUnit.HOURS) {
            format = "yyyyMMddHH";
        } else if (timeUnit == TimeUnit.MINUTES) {
            format = "yyyyMMddHHmm";
        } else if (timeUnit == TimeUnit.SECONDS) {
            format = "yyyyMMddHHmmss";
        }
        return getFormatDate(format);
    }

    /**
     * 获取 "yyyy-MM-dd"格式字符串
     * @return
     */
    public static String getFormatDate() {
        return getFormatDate("yyyy-MM-dd");
    }

    /**
     * 获取"yyyy-MM-dd HH:mm:ss"格式字符串
     * @return
     */
    public static String getFormatDateTime() {
        return getFormatDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将字符串按照指定格式转换成Date对象
     * @param dateStr 字符串
     * @param format 格式字符串
     * @return
     */
    public static Date dataFormat(String dateStr, String format) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        if (format == null || format.equals("")) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字符串按照 "yyyy-MM-dd"格式转化成Date对象
     * @param dateStr
     * @return
     */
    public static Date dataFormatDate(String dateStr) {
        return dataFormat(dateStr, "yyyy-MM-dd");
    }

    /**
     * 将字符串按照 "yyyy-MM-dd HH:mm:ss"格式转化成Date对象
     * @param dateStr
     * @return
     */
    public static Date dataFormatDateTime(String dateStr) {
        return dataFormat(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期对象按照指定格式转换成字符串
     * @param date 日期对象
     * @param format 格式
     * @return
     */
    public static String dateFormat(Date date, String format) {
        if (date == null || format == null || format.equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期对象转换成"yyyy-MM-dd"格式字符串
     * @param date 日期对象
     * @return
     */
    public static String dateFormatDate(Date date) {
        return dateFormat(date, "yyyy-MM-dd");
    }

    /**
     * 将日期对象转换成"yyyy-MM-dd HH:mm:ss"格式字符串
     * @param date 日期对象
     * @return
     */
    public static String dateFormatDateTime(Date date) {
        return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前日期字符串,格式"yyyy-MM-dd"
     * @return
     */
    public static String getCurrentDate() {
        return getFormatDate();
    }

    /**
     * 获取当前日期字符串,格式"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentDateTime() {
        return getFormatDateTime();
    }

    /**
     * 指定时间是否大于当天(截止到23点59分59秒)
     * @param date
     * @return
     */
    public static boolean eqNow(Date date) {
        boolean flag = false;
        if (date != null) {
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.HOUR_OF_DAY, 23);
            ca.set(Calendar.MINUTE, 59);
            ca.set(Calendar.MINUTE, 59);
            if (date.getTime() > ca.getTimeInMillis()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 时间是否在指定范围内
     * @param date 指定时间
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public static boolean isRange(Date date, Date startDate, Date endDate) {
        boolean flag = false;
        long time = 0l;
        long startTime = 0l;
        long endTime = 0l;
        if (date != null) {
            time = date.getTime();
        }
        if (startDate != null) {
            startTime = startDate.getTime();
        }
        if (endDate != null) {
            endTime = endDate.getTime();
        }
        if (time >= startTime && time <= endTime) {
            flag = true;
        }
        return flag;
    }

    /**
     * 时间是否在指定范围之内
     * @param date
     * @param startDate
     * @param endDate
     * @param startEqual
     * @param endEqual
     * @return
     */
    public static boolean isRange(Date date, Date startDate, Date endDate, boolean startEqual, boolean endEqual) {
        boolean flag = false;
        if (date == null || startDate == null || endDate == null) {
            return flag;
        }
        if (date.after(startDate) && date.before(endDate)) {
            if (startEqual && (date.toString().equals(startDate.toString()))) {
                flag = true;
            } else if (endEqual && (date.toString().equals(endDate.toString()))) {
                flag = true;
            } else if (startEqual && endEqual && (date.toString().equals(endDate.toString()))
                    && (date.toString().equals(startDate.toString()))) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取两个日期间隔的天数
     * @param dateStr1 日期格式字符串1
     * @param dateStr2 日期格式字符串2
     * @return
     */
    public static Long getIntervalOfDay(String dateStr1, String dateStr2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //间隔天数
        long interval = 0l;
        try {
            Date date1 = simpleDateFormat.parse(dateStr1);
            Date date2 = simpleDateFormat.parse(dateStr2);
            interval = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return interval;
    }

    /**
     * 获取 [year + "年" + month + "月" + day + "日 " + weekDays]格式日期字符串
     * @param dateStr 日期字符串
     * @param format 格式字符串
     * @return
     */
    public static String getWeekDate(String dateStr, String format) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            calendar.setTime(simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return year + "年" + month + "月" + day + "日 " + weekDays[w];
    }

    /**
     * 获取YYMMDD格式日期的某个月最后一天的日期
     * @param day 日期
     * @param format YYMMDD格式
     * @return
     */
    public static String getCurEndDate(String dayStr, String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            c.setTime(sf.parse(dayStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        c.set(Calendar.DAY_OF_MONTH, 1); // 当月第一天
        c.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        return sf.format(c.getTime());
    }

    /**
     * 获取YYMMDD格式日期的某个月第一天的日期
     * @param day 日期
     * @param format YYMMDD格式
     * @return
     * @throws ParseException
     */
    public static String getCurrBeginDate(String day, String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            c.setTime(sf.parse(day));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        c.set(Calendar.DATE, 1);// 设为当前月的1号
        return sf.format(c.getTime());
    }

    /**
     * 上月第一天
     * @param day
     * @param format
     * @return
     */
    public static String getPreviousMonthFirst(String day, String format) {
        Calendar lastDate = Calendar.getInstance();
        try {
            lastDate.setTime(new SimpleDateFormat(format).parse(day));
            lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
            lastDate.add(Calendar.MONTH, -12);// 减一个月，变为下月的1号
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 获得上月最后一天的日期
     * @param day
     * @param format
     * @return
     */
    public static String getPreviousMonthEnd(String day, String format) {
        Calendar lastDate = Calendar.getInstance();
        try {
            lastDate.setTime(new SimpleDateFormat(format).parse(day));
            lastDate.add(Calendar.MONTH, -12);// 减一个月
            lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
            lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 获取指定间隔的日期
     * @param date
     * @param format
     * @param gap 间隔月数
     * @return
     */
    public static String getFirstDateByGap(String date, String format, Integer monthGap) {
        Calendar lastDate = Calendar.getInstance();
        try {
            lastDate.setTime(new SimpleDateFormat(format).parse(date));
            lastDate.add(Calendar.MONTH, monthGap);// 减一个月
            lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 获取指定日期间隔N个月后当月的最后一天
     * @param date 指定日期字符串
     * @param format 格式
     * @param interval 间隔月数
     * @return
     * @throws Exception
     */
    public static String getLastDateOfInvterval(String date, String format, int interval) {
        Calendar lastDate = Calendar.getInstance();
        try {
            lastDate.setTime(new SimpleDateFormat(format).parse(date));
            lastDate.add(Calendar.MONTH, interval);// 减一个月
            lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
            lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 指定日期间隔N后的日期
     * @param date 指定日期
     * @param interval 间隔
     * @param timeUnit 时间间隔单位
     * @return
     */
    public static String getDateByInterval(String date, int interval, TimeUnit timeUnit) {
        Calendar calendar = Calendar.getInstance();
        String format = "";
        try {
            if (timeUnit == TimeUnit.DAYS) {
                format = "yyyyMMdd";
                calendar.setTime(new SimpleDateFormat(format).parse(date));
                calendar.add(Calendar.DAY_OF_MONTH, interval);
            } else if (timeUnit == TimeUnit.HOURS) {
                format = "yyyyMMddHH";
                calendar.setTime(new SimpleDateFormat(format).parse(date));
                calendar.add(Calendar.HOUR, interval);
            } else if (timeUnit == TimeUnit.MINUTES) {
                format = "yyyyMMddHHmm";
                calendar.setTime(new SimpleDateFormat(format).parse(date));
                calendar.add(Calendar.MINUTE, interval);
            } else if (timeUnit == TimeUnit.SECONDS) {
                format = "yyyyMMddHHmmss";
                calendar.setTime(new SimpleDateFormat(format).parse(date));
                calendar.add(Calendar.SECOND, interval);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获取当前日期间隔一段时间后的日期字符串
     * @param format 日期格式字符串
     * @param interval 时间间隔
     * @param timeUnit 时间间隔单位
     * @return
     */
    public static String getDateStrByInterval(int interval, TimeUnit timeUnit) {
        if (interval < 0) {
            return null;
        }
        return getDateByInterval(getCurrentDate(), interval, timeUnit);
    }

    /**
     * 判断日期是否相等
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equals(Date date1, Date date2) {
        boolean flag = false;
        if (date1 != null && date2 != null && date1.toString().equals(date2.toString())) {
            return flag;
        }
        return flag;
    }

    /**
     * 根据时,分,秒创建一个时间
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static long getTime(int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.clear(Calendar.HOUR_OF_DAY);
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTimeInMillis();
    }

    /**
     * 修改时间
     * @param time
     * @param timeUnit
     * @param interval
     * @return
     */
    public static Date modifyTime(Date time, TimeUnit timeUnit, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(time);
        if (timeUnit == TimeUnit.DAYS) {
            calendar.add(Calendar.DAY_OF_MONTH, interval);
        } else if (timeUnit == TimeUnit.HOURS) {
            calendar.add(Calendar.HOUR, interval);
        } else if (timeUnit == TimeUnit.MINUTES) {
            calendar.add(Calendar.MINUTE, interval);
        } else if (timeUnit == TimeUnit.SECONDS) {
            calendar.add(Calendar.SECOND, interval);
        }
        return calendar.getTime();
    }
}