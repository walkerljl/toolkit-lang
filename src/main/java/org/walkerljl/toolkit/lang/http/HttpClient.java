package org.walkerljl.toolkit.lang.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * Http client
 *
 * @author lijunlin
 */
public class HttpClient {

    /** 64K buffer*/
    private static final int    IO_BUFFER_SZIE  = 64 * 1024;
    private static final String CHARSET_DEFAULT = "UTF-8";
    public final static  String METHOD_POST     = "POST";
    public final static  String METHOD_GET      = "GET";

    private String url;
    private String method;
    private String charset;
    private int                 connectTimeout = 3000;
    private int                 readTimeout    = 3000;
    private Map<String, String> header         = new LinkedHashMap<String, String>();
    private List<Parameter>     params         = new ArrayList<Parameter>();
    /** Not modified flag*/
    private boolean notModified;
    /** Last modified time*/
    private long    lastModified;

    /**
     * Constructor
     */
    public HttpClient() {
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor
     *
     * @param url
     */
    public HttpClient(String url) {
        this.url = url;
    }

    /**
     * Constructor
     *
     * @param url
     * @param method
     */
    public HttpClient(String url, String method) {
        this.url = url;
        this.method = method;
    }

    /**
     * Execute request
     *
     * @return
     * @throws Exception
     */
    public InputStream executeRequest() throws Exception {
        InputStream in = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(getUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            //Set header
            connection.setRequestMethod(getMethod());
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Accept-Charset", getCharset());
            connection.setRequestProperty("contentType", getCharset());
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
            connection.setRequestProperty("Connection", "Keep-Alive");

            //Set timeout
            connection.setConnectTimeout(this.connectTimeout);
            connection.setReadTimeout(this.readTimeout);
            if (lastModified > 0) {
                connection.setIfModifiedSince(lastModified);
            }

            if (METHOD_POST.equalsIgnoreCase(getMethod())) {
                OutputStream out = null;
                try {
                    out = connection.getOutputStream();
                    out.write(getPostData().getBytes(getCharset()));
                    out.flush();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            } else {
                connection.connect();
            }

            //Set header
            for (Entry<String, String> entry : header.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            int resCode = connection.getResponseCode();
            this.notModified = (resCode == HttpURLConnection.HTTP_NOT_MODIFIED);
            setLastModified(connection.getLastModified());
            if (!notModified && resCode != HttpURLConnection.HTTP_OK) {
                String result = connection.getResponseMessage();
                throw new IllegalAccessException("Http request error code :" + resCode + "\n response result: " + result);
            }
            in = connection.getInputStream();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return in;
    }

    /**
     * Execute request
     *
     * @param out
     * @throws Exception
     */
    public void executeRequest(OutputStream out) throws Exception {
        InputStream in = executeRequest();
        try {
            byte[] buffer = new byte[IO_BUFFER_SZIE];
            int read;
            while (true) {
                read = in.read(buffer, 0, IO_BUFFER_SZIE);
                if (read == -1) {
                    break;
                }
                out.write(buffer, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Execute request and response string data
     *
     * @return
     * @throws Exception
     */
    public String executeRequestResString() throws Exception {
        InputStream in = executeRequest();
        try {
            byte[] buffer = new byte[IO_BUFFER_SZIE];
            int length = in.read(buffer);
            byte[] data = new byte[length];
            System.arraycopy(buffer, 0, data, 0, length);
            String context = new String(data, getCharset());
            return context;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Get need post data
     *
     * @return
     */
    private String getPostData() {
        StringBuilder postData = new StringBuilder();
        for (Parameter entry : params) {
            postData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return postData.toString();
    }

    /**
     * Set last modified time
     *
     * @param lastModified
     */
    private void setLastModified(long lastModified) {
        if (lastModified > 0) {
            this.lastModified = lastModified;
        }
    }

    /**
     * Add parameter
     *
     * @param key
     * @param value
     */
    public void addParameter(String key, Object value) {
        if (key != null && value != null) {
            params.add(new Parameter(key, value));
        }
    }

    /**
     * Add header
     *
     * @param key
     * @param value
     */
    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    /**
     * Get url
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set url
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get do request method.
     *
     * @return
     */
    public String getMethod() {
        if (method == null || "".equals(method)) {
            method = METHOD_POST;
        }
        return method;
    }

    /**
     * Set request method
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Get charset
     *
     * @return
     */
    public String getCharset() {
        if (charset == null || "".equals(charset)) {
            charset = CHARSET_DEFAULT;
        }
        return charset;
    }

    /**
     * Set charset
     *
     * @param charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * Set time out for connect
     * @param connectTimeout
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * Set time out for read
     *
     * @param readTimeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
     * Is not modified
     *
     * @return
     */
    public boolean isNotModified() {
        return notModified;
    }

    /**
     * Get last modified time
     *
     * @return
     */
    public long getLastModified() {
        return lastModified;
    }

    /**
     * Parameter
     *
     * @author lijunlin
     */
    private class Parameter {
        private String key;
        private Object value;

        public Parameter(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}