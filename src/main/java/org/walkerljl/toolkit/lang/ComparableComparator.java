/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.util.Comparator;

/**
 * Comparator that adapts <code>Comparables</code> to the <code>Comparator</code> interface.
 */
public class ComparableComparator<T extends Comparable<T>> implements Comparator<T> {

    /**
     * Cached instance.
     */
    public static final ComparableComparator INSTANCE = new ComparableComparator();

    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

}
