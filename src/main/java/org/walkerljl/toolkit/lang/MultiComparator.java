/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.util.Comparator;
import java.util.List;

/**
 * Multiple comparators compares using list of comparators.
 */
public class MultiComparator<T> implements Comparator<T> {

    protected final List<Comparator<T>> comparators;

    public MultiComparator(List<Comparator<T>> comparators) {
        this.comparators = comparators;
    }

    /**
     * Compares two objects starting with first comparator; if they are equals
     * proceeds to the next comparator and so on.
     */
    public int compare(T o1, T o2) {
        int comparatorsSize = comparators.size();

        for (int i = 0; i < comparatorsSize; i++) {
            Comparator<T> comparator = comparators.get(i);

            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
