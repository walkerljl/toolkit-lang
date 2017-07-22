/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.lang;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.walkerljl.toolkit.lang.io.StreamUtils;

/**
 * Map file extensions to MIME types. Based on the most recent Apache mime.types file.
 * Duplicated extensions (wmz, sub) are manually resolved.
 * <p>
 * See also:
 * http://www.iana.org/assignments/media-types/
 * http://www.webmaster-toolkit.com/mime-types.shtml
 */
public class MimeTypes {

    public static final String MIME_APPLICATION_ATOM_XML     = "application/atom+xml";
    public static final String MIME_APPLICATION_JAVASCRIPT   = "application/javascript";
    public static final String MIME_APPLICATION_JSON         = "application/json";
    public static final String MIME_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_APPLICATION_XML          = "application/xml";
    public static final String MIME_TEXT_CSS                 = "text/css";
    public static final String MIME_TEXT_PLAIN               = "text/plain";
    public static final String MIME_TEXT_HTML                = "text/html";

    private static final HashMap<String, String> MIME_TYPE_MAP;    // extension -> mime-type map

    static {
        InputStream is = MimeTypes.class.getResourceAsStream(MimeTypes.class.getSimpleName() + ".properties");
        if (is == null) {
            throw new IllegalStateException("Mime types file missing");
        }

        Properties mimes = null;
        try {
            mimes = PropertiesUtils.createFromInputStream(is);
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage());
        } finally {
            StreamUtils.close(is);
        }

        MIME_TYPE_MAP = new HashMap<String, String>(mimes.size() * 2);

        Enumeration<?> keys = mimes.propertyNames();
        while (keys.hasMoreElements()) {
            String mimeType = (String) keys.nextElement();
            String extensions = mimes.getProperty(mimeType);

            if (mimeType.startsWith("/")) {
                mimeType = "application" + mimeType;
            } else if (mimeType.startsWith("a/")) {
                mimeType = "audio" + mimeType.substring(1);
            } else if (mimeType.startsWith("i/")) {
                mimeType = "image" + mimeType.substring(1);
            } else if (mimeType.startsWith("t/")) {
                mimeType = "text" + mimeType.substring(1);
            } else if (mimeType.startsWith("v/")) {
                mimeType = "video" + mimeType.substring(1);
            }

            String[] allExtensions = StringUtils.split(extensions, "");

            for (String extension : allExtensions) {
                if (MIME_TYPE_MAP.put(extension, mimeType) != null) {
                    throw new IllegalArgumentException("Duplicated extension: " + extension);
                }
            }
        }
    }

    /**
     * Registers MIME type for provided extension. Existing extension type will be overridden.
     */
    public static void registerMimeType(String ext, String mimeType) {
        MIME_TYPE_MAP.put(ext, mimeType);
    }

    /**
     * Returns the corresponding MIME type to the given extension.
     * If no MIME type was found it returns <code>application/octet-stream</code> type.
     */
    public static String getMimeType(String ext) {
        String mimeType = lookupMimeType(ext);
        if (mimeType == null) {
            mimeType = MIME_APPLICATION_OCTET_STREAM;
        }
        return mimeType;
    }

    /**
     * Simply returns MIME type or <code>null</code> if no type is found.
     */
    public static String lookupMimeType(String ext) {
        return MIME_TYPE_MAP.get(ext.toLowerCase());
    }

    /**
     * Finds all extensions that belong to given mime type(s).
     * If wildcard mode is on, provided mime type is wildcard pattern.
     * @param mimeType list of mime types, separated by comma
     * @param useWildcard if set, mime types are wildcard patterns
     */
    /**
     public static String[] findExtensionsByMimeTypes(String mimeType, boolean useWildcard) {
     ArrayList<String> extensions = new ArrayList<String>();

     mimeType = mimeType.toLowerCase();
     String[] mimeTypes = StringUtil.splitc(mimeType, ", ");

     for (Map.Entry<String, String> entry : MIME_TYPE_MAP.entrySet()) {
     String entryExtension = entry.getKey();
     String entryMimeType = entry.getValue().toLowerCase();

     int matchResult = useWildcard ?
     Wildcard.matchOne(entryMimeType, mimeTypes) :
     StringUtil.equalsOne(entryMimeType, mimeTypes);

     if (matchResult != -1) {
     extensions.add(entryExtension);
     }
     }

     if (extensions.isEmpty()) {
     return StringPool.EMPTY_ARRAY;
     }

     return extensions.toArray(new String[extensions.size()]);
     }*/
}