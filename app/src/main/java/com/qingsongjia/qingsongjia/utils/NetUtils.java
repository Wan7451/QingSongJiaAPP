package com.qingsongjia.qingsongjia.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wan7451.base.WanActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wanggang on 15/11/29.
 */
public class NetUtils {

//    private static final String BASE_URL = "http://360c.tarena.com.cn/";
    private static final String BASE_URL = "http://192.168.232.12:8080/DrivingPlatform/";
//    private static final String BASE_URL = "http://192.168.232.31:8080/DrivingPlatform/";
    private static OkHttpClient mOkHttpClient;
    private static Handler mOKHandler;
    private static final boolean DEBUG = true;

    private static final String TAG = "==========";

    private static final HashMap<Integer, String> erroeCode;//错误码

    static {
        erroeCode = new HashMap<>();
        erroeCode.put(403, "无法访问该资源");
        erroeCode.put(404, "网络受限制或找不到该资源");
        erroeCode.put(500, "后台处理数据出错");
        erroeCode.put(505, "服务器连接超时");
        erroeCode.put(999, "请登陆！");
        erroeCode.put(721, "该时间教练已被越练,请重新选择约练教练");
        erroeCode.put(722, "该学员此时间已经约练,请更换时间约练");
        erroeCode.put(731, "该手机号码已被注册");
        erroeCode.put(732, "用户名或密码输入有误");
        erroeCode.put(733, "该用户不存在");
        erroeCode.put(734, "验证码不通过");
        erroeCode.put(741, "该优惠券已超过有效期");
    }

    public static void baseRequest(Context context, String path,
                                   HashMap<String, String> params,
                                   boolean isShowLoading,
                                   final NetUtilsHandler handler) {

        //创建okHttpClient对象
        if (mOkHttpClient == null)
            mOkHttpClient = new OkHttpClient();
        if (mOKHandler == null)
            mOKHandler = new Handler();

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("拼命加载中.....");

        if (isShowLoading) {
            dialog.show();
        }

        StringBuffer sb = new StringBuffer();
        sb.append(BASE_URL);
        sb.append(path);
        //拼接参数
        if (params != null && params.size() > 0) {
            sb.append("?");
            Iterator iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                if (!TextUtils.isEmpty(val)) {
                    sb.append(key);
                    sb.append("=");
                    sb.append(val);
                    sb.append("&");
                }
            }
            sb.setLength(sb.length() - 1);
        }

        if (DEBUG) {
            Log.e(TAG, "【URL】" + sb.toString());
        }

        //创建一个Request
        final Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                        if (DEBUG) {
                            Log.e(TAG, "【ERROR】" + e.getLocalizedMessage());
                        }

                        mOKHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mOKHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.cancel();
                                        handler.onResponseError("您的网络有问题，检查下再回来~");
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onResponse(final Response response) {

                        if (response != null && response.isSuccessful()) {
                            String result = "";
                            try {
                                result = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (DEBUG) {
                                Log.e(TAG, "【OK】" + result);
                            }

                            if (!TextUtils.isEmpty(result)) {
                                JSONObject object = JSONObject.parseObject(result);
                                int STATE = object.getInteger("STATE");
                                final int CODE = object.getInteger("CODE");

                                //访问成功！
                                if (STATE == 0 && CODE == 200) {
                                    JSONObject DATA = null;
                                    if (object.containsKey("DATA")) {
                                        DATA = object.getJSONObject("DATA");
                                    }
                                    if (DEBUG) {
                                        Log.e(TAG, "【OK】" + result);
                                    }
                                    final JSONArray list;
                                    final int total;
                                    if (DATA!=null && DATA.containsKey("list"))
                                        list = DATA.getJSONArray("list");
                                    else
                                        list = null;
                                    if (DATA!=null && DATA.containsKey("total"))
                                        total = DATA.getInteger("total");
                                    else
                                        total = 0;
                                    mOKHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.cancel();
                                            handler.onResponseOK(list, total);
                                        }
                                    });
                                    return;
                                }

                                if (erroeCode.containsKey(CODE)) {
                                    mOKHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.cancel();
                                            handler.onResponseError(erroeCode.get(CODE));
                                        }
                                    });
                                } else {
                                    mOKHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.cancel();
                                            handler.onResponseError("服务器正在闹脾气，等会再访问她~");
                                        }
                                    });
                                }
                                return;
                            }
                            mOKHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.cancel();
                                    handler.onResponseError("服务器正在闹脾气，等会再访问她~");
                                }
                            });
                            return;
                        }
                        mOKHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                                handler.onResponseError("您的网络有问题，检查下再回来~");
                            }
                        });

                    }
                }
        );


    }




    public interface NetUtilsHandler {
        void onResponseOK(JSONArray response, int total);

        void onResponseError(String error);
    }
}
