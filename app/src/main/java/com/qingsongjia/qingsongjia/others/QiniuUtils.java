package com.qingsongjia.qingsongjia.others;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.android.utils.UrlSafeBase64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wanggang on 15/12/26.
 */
public class QiniuUtils {

    public static final String sk = "jYwlKfs605nrNVz5tdKxQ9IUzegG_IKNi1EQPHYl";
    public static final String ak = "p8xOqvSh-zym4DysE8nJ7EmrIgzMzaaLI9X8EeaW";

    public static final String IMAGE_URL_HEADER="http://7xlt5l.com1.z0.glb.clouddn.com/";

    public static String getUploadToken() {
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", "driverplatform");
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json
                    .toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, sk);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            return ak + ':' + _encodedSign + ':'
                    + _encodedPutPolicy;
        } catch (Exception e) {

        }
        return null;
    }

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }
}
