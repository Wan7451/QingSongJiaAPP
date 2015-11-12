package com.wan7451.socialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * HTTP工具类
 *
 * @author Tom
 */
public class HttpsUtil {


    /**
     * 以URL方式发送数据,POST
     *
     * @param urlStr     发送地址
     * @param contentStr 发送内容
     * @param charset    发送字符集
     * @param sResult    返回数据StringBuilder
     * @return boolean 发送是否成功
     */
    public static boolean sendJPlusHttpsStrOfPost(String urlStr, String contentStr, String charset,
                                                  StringBuilder sResult, String base64) {
        boolean bResult = false;
        String charsetName = charset;
        URL url = null;
        HttpsURLConnection httpsConnection = null;
        InputStream httpIS = null;
        java.io.BufferedReader http_reader = null;
        try {
            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            url = new URL(urlStr);
            httpsConnection = (HttpsURLConnection) url.openConnection();

            httpsConnection.setSSLSocketFactory(sc.getSocketFactory());
            httpsConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());


            // 设置连接主机超时(单位:毫秒)
            httpsConnection.setConnectTimeout(180000);
            // 设置从主机读取数据超时(单位:毫秒)
            httpsConnection.setReadTimeout(180000);

            httpsConnection.setRequestProperty("Authorization", base64);
            httpsConnection.setRequestMethod("POST"); // POST方式提交数据
            httpsConnection.setDoOutput(true);
            httpsConnection.setRequestProperty("Content-Length", String.valueOf(contentStr.getBytes().length));
//            httpsConnection.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = null;
            out = new PrintWriter(new OutputStreamWriter(httpsConnection.getOutputStream(), charsetName));// 此处改动
            // 发送请求
            out.print(contentStr);
            out.flush();
            out.close();
            int responseCode = httpsConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 发送正常
                bResult = true;

                // 读取数据
                httpIS = httpsConnection.getInputStream();
                http_reader = new java.io.BufferedReader(new java.io.InputStreamReader(httpIS, charsetName));
                String line = null;
                while ((line = http_reader.readLine()) != null) {
                    if (sResult.length() > 0) {
                        sResult.append("\n");
                    }
                    sResult.append(line);
                }
            } else {
                // 读取数据
                httpIS = httpsConnection.getInputStream();
                http_reader = new java.io.BufferedReader(new java.io.InputStreamReader(httpIS, charsetName));
                String line = null;
                while ((line = http_reader.readLine()) != null) {
                    if (sResult.length() > 0) {
                        sResult.append("\n");
                    }
                    sResult.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (http_reader != null) http_reader.close();
                if (httpIS != null) httpIS.close();
                if (httpsConnection != null) httpsConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bResult;
    }


    /**
     * 以URL方式发送数据,GET
     *
     * @param urlStr     发送地址
     * @param contentStr 发送内容
     * @param charset    发送字符集
     * @param sResult    返回数据StringBuilder
     * @return boolean 发送是否成功
     */
    public static boolean sendHttpsStrOfGet(String urlStr, String contentStr, String charset,
                                            StringBuilder sResult, String base64) {
        boolean bResult = false;
        String charsetName = charset;
        URL url = null;
        HttpsURLConnection httpsConnection = null;
        InputStream httpIS = null;
        java.io.BufferedReader http_reader = null;
        try {
            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            url = new URL(urlStr);
            httpsConnection = (HttpsURLConnection) url.openConnection();

            httpsConnection.setSSLSocketFactory(sc.getSocketFactory());
            httpsConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());


            // 设置连接主机超时(单位:毫秒)
            httpsConnection.setConnectTimeout(180000);
            // 设置从主机读取数据超时(单位:毫秒)
            httpsConnection.setReadTimeout(180000);

            httpsConnection.setRequestProperty("Authorization", base64);
            httpsConnection.setRequestMethod("GET"); // GET方式提交数据
            httpsConnection.setDoOutput(true);
            httpsConnection.setRequestProperty("Content-Length", String.valueOf(contentStr.getBytes().length));
            httpsConnection.setRequestProperty("Content-Type", "application/json");

            httpsConnection.connect();
            int responseCode = httpsConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 发送正常
                bResult = true;

                // 读取数据
                httpIS = httpsConnection.getInputStream();
                http_reader = new java.io.BufferedReader(new java.io.InputStreamReader(httpIS, charsetName));
                String line = null;
                while ((line = http_reader.readLine()) != null) {
                    if (sResult.length() > 0) {
                        sResult.append("\n");
                    }
                    sResult.append(line);
                }
            } else {
                // 读取数据
                httpIS = httpsConnection.getInputStream();
                http_reader = new java.io.BufferedReader(new java.io.InputStreamReader(httpIS, charsetName));
                String line = null;
                while ((line = http_reader.readLine()) != null) {
                    if (sResult.length() > 0) {
                        sResult.append("\n");
                    }
                    sResult.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (http_reader != null) http_reader.close();
                if (httpIS != null) httpIS.close();
                if (httpsConnection != null) httpsConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bResult;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}