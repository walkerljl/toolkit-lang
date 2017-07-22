/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Scanf
 *
 * @author lijunlin
 */
public class Scanf {

    protected static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Scans input console and returns entered string.
     * @return
     * @throws IOException
     */
    public static String readLine() throws IOException {
        return in.readLine();
    }
}