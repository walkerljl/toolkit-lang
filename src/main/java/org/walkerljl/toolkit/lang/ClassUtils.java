/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.net.URL;

/**
 * ClassUtils
 *
 * @author lijunlin
 */
public class ClassUtils {

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     * @return
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    /**
     * 加载类（将自动初始化）
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载类出错," + e.getMessage(), e);
        }
        return cls;
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtils.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建实例出错," + e.getMessage(), e);
        }
        return instance;
    }

    /**
     * 是否为 long 类型（包括 Long 类型）
     * @param type
     * @return
     */
    public static boolean isInt(Class<?> type) {
        return type.equals(int.class) || type.equals(Integer.class);
    }

    /**
     * 是否为 long 类型（包括 Long 类型）
     * @param type
     * @return
     */
    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    /**
     * 是否为 double 类型（包括 Double 类型）
     * @param type
     * @return
     */
    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    /**
     * 是否为 String 类型
     * @param type
     * @return
     */
    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }
}