package com.qingsongjia.qingsongjia.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wanggang on 15/11/29.
 */
public class NetUtils {

    private static final String BASE_URL = "http://360c.tarena.com.cn/";
    private static OkHttpClient mOkHttpClient;
    private static Handler mOKHandler;
    private static final boolean DEBUG = true;

    private static final String TAG = "==========";

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
                sb.append(key);
                sb.append("=");
                sb.append(val);
                sb.append("&");
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
                                int CODE = object.getInteger("CODE");
                                JSONObject DATA = object.getJSONObject("DATA");

                                //访问成功！
                                if (STATE == 0 && CODE == 200) {

                                    if (DEBUG) {
                                        Log.e(TAG, "【OK】" + result);
                                    }

                                    final JSONArray list = DATA.getJSONArray("list");
                                    final int total = DATA.getInteger("total");
                                    mOKHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.cancel();
                                            handler.onResponseOK(list, total);
                                        }
                                    });
                                    return;
                                }
                                mOKHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.cancel();
                                        handler.onResponseError("服务器正在闹脾气，等会再访问她~");
                                    }
                                });
                            }
                            mOKHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.cancel();
                                    handler.onResponseError("服务器正在闹脾气，等会再访问她~");
                                }
                            });
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
