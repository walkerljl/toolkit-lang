/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.omg.SendingContext.RunTime;
import org.walkerljl.toolkit.lang.io.StreamUtils;

/**
 *
 * PropertiesUtils
 *
 * @author lijunlin
 */
public class PropertiesUtils {

    public static Properties createFromFile(String fileName) {
        return createFromFile(new File(fileName));
    }

    public static Properties createFromFile(File file) {
        Properties prop = new Properties();
        loadFromFile(prop, file);
        return prop;
    }

    public static void loadFromFile(Properties properties, String fileName) {
        loadFromFile(properties, new File(fileName));
    }

    public static void loadFromFile(Properties properties, File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadFromInputStream(properties, in);
    }

    public static void loadFromInputStream(Properties properties, InputStream in) {
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            StreamUtils.close(in);
        }
    }

    public static Properties createFromInputStream(InputStream in) {
        Properties properties = new Properties();
        loadFromInputStream(properties, in);
        return properties;
    }

    public static void writeToFile(Properties properties, String fileName) {
        writeToFile(properties, new File(fileName), null);
    }

    public static void writeToFile(Properties properties, String fileName, String header) {
        writeToFile(properties, new File(fileName), header);
    }

    public static void writeToFile(Properties properties, File file) {
        writeToFile(properties, file, null);
    }

    public static void writeToFile(Properties properties, File file, String header) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            properties.store(fos, header);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            StreamUtils.close(fos);
        }
    }

    public static String getPropertyAsString(Properties properties, String key) {
        return getPropertyAsString(properties, key, "");
    }

    public static String getPropertyAsString(Properties properties, String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        return value == null ? null : value.trim();
    }

    public static byte getPropertyAsByte(Properties properties, String key) {
        return getPropertyAsByte(properties, key, (byte) 0);
    }

    public static byte getPropertyAsByte(Properties properties, String key, byte defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Byte.valueOf(value);
    }

    public static short getPropertyAsChar(Properties properties, String key, short defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Short.valueOf(value);
    }

    public static int getPropertyAsInt(Properties properties, String key) {
        return getPropertyAsInt(properties, key, 0);
    }

    public static int getPropertyAsInt(Properties properties, String key, int defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Integer.valueOf(value);
    }

    public static long getPropertyAsLong(Properties properties, String key) {
        return getPropertyAsLong(properties, key, 0L);
    }

    public static long getPropertyAsLong(Properties properties, String key, long defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Long.valueOf(value);
    }

    public static char getPropertyAsChar(Properties properties, String key, char defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : value.charAt(0);
    }

    public static float getPropertyAsFloat(Properties properties, String key) {
        return getPropertyAsFloat(properties, key, 0.0f);
    }

    public static float getPropertyAsFloat(Properties properties, String key, float defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Float.valueOf(value);
    }

    public static double getPropertyAsDouble(Properties properties, String key) {
        return getPropertyAsDouble(properties, key, 0.0);
    }

    public static double getPropertyAsDouble(Properties properties, String key, double defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Double.valueOf(value);
    }

    public static boolean getPropertyAsBoolean(Properties properties, String key) {
        return getPropertyAsBoolean(properties, key, false);
    }

    public static boolean getPropertyAsBoolean(Properties properties, String key, boolean defaultValue) {
        String value = getPropertyAsString(properties, key, null);
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

    public static String optionAsString(String[] args, String opt, String defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(opt)) {
                if (i < args.length - 1) {return args[i + 1];}
            }
        }
        return defaultValue;
    }

    public static int optionAsInt(String[] args, String opt, int defaultValue) {
        String value = optionAsString(args, opt, null);
        if (value == null) {
            return defaultValue;
        }
        return Integer.valueOf(value);
    }

    public static boolean optionAsBoolean(String[] args, String opt, boolean defaultValue) {
        String value = optionAsString(args, opt, null);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.valueOf(value);
    }
}