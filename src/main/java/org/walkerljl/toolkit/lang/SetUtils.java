/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.util.HashSet;
import java.util.Set;

/**
 * SetUtils
 * @author lijunlin
 */
public class SetUtils {

    private SetUtils() {}

    /**
     * 创建一个HashSet
     * @return
     */
    public static <E> Set<E> newHashSet() {
        return new HashSet<E>();
    }

    /**
     * 创建一个HashSet
     * @param elements
     * @return
     */
    public static <E> Set<E> newHashSet(E... elements) {
        Set<E> set = SetUtils.newHashSet();
        if (ArraysUtils.isNotEmpty(elements)) {
            for (E element : elements) {
                set.add(element);
            }
        }
        return set;
    }

    public static <E> boolean contains(Set<E> set, E element) {
        return set == null ? false : set.contains(element);
    }

    public static <E> Set<E> diff(Set<E> set1, Set<E> set2) {
        if (CollectionUtils.isEmpty(set1)) {
            return null;
        }
        if (CollectionUtils.isEmpty(set2)) {
            return set1;
        }
        Set<E> result = SetUtils.newHashSet();
        for (E element : set1) {
            if (!set2.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}