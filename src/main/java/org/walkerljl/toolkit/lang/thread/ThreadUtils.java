/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang.thread;

/**
 * ThreadUtils
 * @author lijunlins
 */
public final class ThreadUtils {

    private ThreadUtils() {}

    /**
     * Puts a thread to sleep, without throwing an InterruptedException.
     *
     * @param ms     the length of time to sleep in milliseconds
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException iex) {
            // ignore
        }
    }

    /**
     * Puts a thread to sleep forever.
     */
    public static void sleep() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException iex) {
            // ignore
        }
    }

    /**
     * Waits for a object for synchronization purposes.
     */
    public static void wait(Object obj) {
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException inex) {
                //ignore
            }
        }
    }

    /**
     * Waits for a object or a timeout for synchronization purposes.
     */
    public static void wait(Object obj, long timeout) {
        synchronized (obj) {
            try {
                obj.wait(timeout);
            } catch (InterruptedException inex) {
                // ignore
            }
        }
    }

    /**
     * Notifies an object for synchronization purposes.
     */
    public static void notify(Object obj) {
        synchronized (obj) {
            obj.notify();
        }
    }

    /**
     * Notifies an object for synchronization purposes.
     */
    public static void notifyAll(Object obj) {
        synchronized (obj) {
            obj.notifyAll();
        }
    }

    public static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException inex) {
            // ignore
        }
    }

    public static void join(Thread thread, long millis) {
        try {
            thread.join(millis);
        } catch (InterruptedException inex) {
            // ignore
        }
    }

    public static void join(Thread thread, long millis, int nanos) {
        try {
            thread.join(millis, nanos);
        } catch (InterruptedException inex) {
            // ignore
        }
    }
}