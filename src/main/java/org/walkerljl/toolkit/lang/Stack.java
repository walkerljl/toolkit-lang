/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Stack
 * @author lijunlin
 */
public class Stack<E> {

    private int size = 0;

    private List<E> elements = new ArrayList<E>();

    public Stack() {}

    /**
     * push
     *
     * @param element
     */
    public void push(E element) {
        if (this.elements.size() > this.size) {
            this.elements.set(this.size, element);
        } else {
            this.elements.add(element);
        }

        this.size++;
    }

    /**
     * pop
     *
     * @return the last element
     */
    public E pop() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }

        return this.elements.set(--this.size, null);
    }

    /**
     * peek.
     *
     * @return the last element.
     */
    public E peek() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }

        return this.elements.get(this.size - 1);
    }

    /**
     * get.
     *
     * @param index index.
     * @return element.
     */
    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return index < 0 ? this.elements.get(index + this.size) : this.elements.get(index);
    }

    /**
     * set.
     *
     * @param index index.
     * @param value element.
     * @return old element.
     */
    public E set(int index, E value) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return this.elements.set(index < 0 ? index + this.size : index, value);
    }

    /**
     * remove.
     *
     * @param index
     * @return element
     */
    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        E ret = this.elements.remove(index < 0 ? index + this.size : index);
        this.size--;

        return ret;
    }

    /**
     * get stack size.
     *
     * @return size.
     */
    public int size() {
        return this.size;
    }

    /**
     * is empty.
     *
     * @return empty or not.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * clear stack.
     */
    public void clear() {
        this.size = 0;
        this.elements.clear();
    }
}