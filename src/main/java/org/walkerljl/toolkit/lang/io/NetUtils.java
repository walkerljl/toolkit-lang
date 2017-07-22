/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.lang.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

/**
 * IP and Port Helper for RPC
 *
 * @author lijunlin
 * @since 2013-11-1
 */
public final class NetUtils {

    public static final String ANYHOST = "0.0.0.0";

    private static final int RND_PORT_START = 30000;

    private static final int RND_PORT_RANGE = 10000;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private NetUtils() {}

    /**
     * 获取一个随机端口
     *
     * @return
     */
    public static int getRandomPort() {
        return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
    }

    public static int getAvailablePort() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(null);
            return ss.getLocalPort();
        } catch (IOException e) {
            return getRandomPort();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {}
            }
        }
    }

    /**
     * 转换成一个URL字符串
     *
     * @param protocol 协议
     * @param host 地址
     * @param port 端口
     * @param path 资源路径
     * @return
     */
    public static String toURL(String protocol, String host, int port, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append("://");
        sb.append(host).append(':').append(port);
        if (path.charAt(0) != '/') {
            sb.append('/');
        }
        sb.append(path);

        return sb.toString();
    }

    public static String getLogHost() {
        return "127.0.0.1";
    }
}