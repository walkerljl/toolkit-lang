package org.walkerljl.toolkit.lang;

/**
 * HtmlUtils
 * @author lijunlin
 */
public final class HtmlUtils {

    private HtmlUtils() {}

    public static String escapeJson(String json) {
        return json.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
