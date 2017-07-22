/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * URLDecoderUtils
 *
 * @author lijunlin
 */
public class URLDecoderUtils {

    public static String decode(String url, String encondig) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(url, encondig);
        } catch (UnsupportedEncodingException ignored) {
        }
        return decoded;
    }
}
