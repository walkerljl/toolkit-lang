/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ListUtils
 *
 * @author lijunlin
 */
public class ListUtils {

    private ListUtils() {}

    public static <T> boolean isEmpty(List<T> ls) {
        return (ls == null || ls.size() == 0);
    }

    public static <T> boolean isNotEmpty(List<T> ls) {
        return !isEmpty(ls);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> List<T> newArrayList(T t) {
        List<T> ls = new ArrayList<T>();
        ls.add(t);
        return ls;
    }

    public static <T> List<T> newArrayList(T[] array) {
        List<T> ls = new ArrayList<T>();
        if (array != null && array.length > 0) {
            for (T item : array) {
                ls.add(item);
            }
        }
        return ls;
    }

    public static <T> int size(List<T> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 将一个List转换成Map
     * @param ls
     * @return
     */
    public static <K> Map<K, String> asMap(List<K> ls) {
        Map<K, String> map = new HashMap<K, String>();
        if (ls != null && ls.size() > 0) {
            for (K item : ls) {
                map.put(item, "");
            }
        }
        return map;
    }

    /**
     * 将ls2中的元素put到ls1中
     * @param ls1
     * @param ls2
     */
    public static <T> void merge(List<T> ls1, List<T> ls2) {
        if (ls1 == null || ls2 == null) {
            return;
        }
        for (T item : ls2) {
            if (!ls1.contains(item)) {
                ls1.add(item);
            }
        }
    }

    public static <T> T first(List<T> ls) {
        T element = null;
        if (ls != null && ls.size() > 0) {
            element = ls.get(0);
        }
        return element;
    }

    public static <T> T get(List<T> ls, int index) {
        T element = null;
        if (ls != null && index < ls.size()) {
            element = ls.get(index);
        }
        return element;
    }

    /**
     * 取出ls1不存在于ls2中的元素
     * @param ls1
     * @param ls2
     * @return
     */
    public static <T> List<T> listDiffElements(List<T> ls1, List<T> ls2) {
        List<T> ls = new ArrayList<T>();
        if (ls1 != null && ls2 != null) {
            for (T item : ls1) {
                if (!ls2.contains(item)) {
                    ls.add(item);
                }
            }
        }
        return ls;
    }

    /**
     * 将ls装换成string对象
     * @param ls
     * @param regex 元素之前的分隔符
     * @return
     */
    public static <T> String toString(List<T> ls, String regex) {
        StringBuilder sb = new StringBuilder();
        if (isNotEmpty(ls)) {
            for (T item : ls) {
                sb.append(item).append(regex);
            }
        }

        if (sb.length() > regex.length()) {
            sb = sb.replace(sb.length() - regex.length(), sb.length(), "");
        }

        return sb.toString();
    }

    public static <T> void traverse(List<T> ls) {
        if (isNotEmpty(ls)) {
            for (T item : ls) {
                System.out.println(item);
            }
        }
    }

    /**
     * 过滤重复的元素
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> filterDuplicatedElement(List<T> list) {
        if (ListUtils.isEmpty(list)) {
            return null;
        }
        List<T> reuslt = ListUtils.newArrayList();
        Set<T> exists = new HashSet<T>();
        for (T ele : list) {
            if (exists.contains(ele)) {
                continue;
            }
            reuslt.add(ele);
            exists.add(ele);
        }
        return reuslt;
    }
}