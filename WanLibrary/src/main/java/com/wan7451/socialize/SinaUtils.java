package com.wan7451.socialize;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

/**
 * 新浪工具
 * <p>
 * <p>
 * <p>
 * <p>
 * access_key=====644883
 * access_secret=====2491766721
 * uid=2491766721
 * favourites_count=1
 * location=北京 东城区
 * description=
 * verified=false
 * friends_count=27
 * gender=1
 * screen_name=wan7451
 * statuses_count=72
 * followers_count=23
 * profile_image_url=http://tp2.sinaimg.cn/2491766721/180/0/1
 * access_token=2.00p2MdiC06IIg231737b342e0o2Px1
 * <p>
 * <p>
 * <p>
 * Set<String> set = value.keySet();
 * //获得迭代器
 * Iterator it = set.iterator();
 * //判断是否还有元素可以迭代
 * while (it.hasNext()) {
 * Log.d("TestData", it.next() + "=====" + value.get(it.next() + "") + "");
 * }
 */
public class SinaUtils {


    public static final String FRIENDS_URL = "https://api.weibo.com/2/friendships/friends.json";
    private static final String PUSH_URL = "https://api.weibo.com/2/statuses/update.json";

    public static void auth(final Activity context, final OnThirdLoginListener l) {

        ThridLogin sinaData = SocializeLocalData.getSinaData(context);
        if (sinaData != null && l != null) {
            l.ok(sinaData);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("新浪微博登录中...");
        dialog.show();

        final UMSocialService mController = SocializeConstant.getUMController();

        mController.getConfig().setSsoHandler(new SinaSsoHandler());

        final ThridLogin data = new ThridLogin();

        mController.doOauthVerify(context, SHARE_MEDIA.SINA, new SocializeListeners.UMAuthListener() {
            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {
                    //获取 uid
                    if (value.containsKey("uid")) {
                        data.setUid(value.getString("uid"));
                    }

                    mController.getPlatformInfo(context, SHARE_MEDIA.SINA, new SocializeListeners.UMDataListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onComplete(int status, Map<String, Object> info) {
                            dialog.dismiss();

                            if (status == 200 && info != null) {
                                data.setAccess_token(info.get("access_token").toString());
                                data.setHeadimgurl(info.get("profile_image_url").toString());
                                data.setNickname(info.get("screen_name").toString());
                                data.setSex((Integer) info.get("gender"));

                                SocializeLocalData.saveSinaData(context, data);

                                if (l != null) {
                                    l.ok(data);
                                }
                            } else {
                                if (l != null) {
                                    l.error("获取数据失败");
                                    Toast.makeText(context, "发生错误：" + status, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                } else {
                    if (l != null) {
                        l.error("授权失败");
                        Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                dialog.dismiss();
            }

            @Override
            public void onStart(SHARE_MEDIA platform) {
            }
        });

    }

    public static void getFriends(final Activity context, final int cursor, final OnThridFriendsListener l) {

        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {

                RequestQueue queue = Volley.newRequestQueue(context);

                String path = FRIENDS_URL + "?source=320344105&access_token=" + data.getAccess_token() + "&uid=" + data.getUid() + "&cursor=" + cursor;

                final StringRequest request = new StringRequest(path, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject object = JSON.parseObject(response);
                        JSONArray users = object.getJSONArray("users");
                        int next_cursor = object.getInteger("next_cursor");
                        int previous_cursor = object.getInteger("previous_cursor");
                        int total_number = object.getInteger("total_number");
                        ArrayList<SinaFriend> friends = new ArrayList<SinaFriend>();
                        friends.clear();
                        friends.addAll(JSONArray.parseArray(users.toJSONString(), SinaFriend.class));

                        if (l != null) {
                            l.ok(friends, next_cursor);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        l.error("获取好友列表失败");
                    }
                });
                queue.add(request);
            }

            @Override
            public void error(String text) {
                l.error("新浪授权失败");
            }
        });
    }


    public static void pushWeiBo(final Activity context, final String status, final OnThridPushListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(final ThridLogin data) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            String sinaUrl = "https://api.weibo.com/2/statuses/update.json";
                            final StringBuilder sResult = new StringBuilder();
                            String contentStr = "status=" + URLEncoder.encode(status, "utf-8");
                            HttpsUtil.sendJPlusHttpsStrOfPost(sinaUrl, contentStr, "UTF-8", sResult, "OAuth2 " + "2.00p2MdiC06IIg231737b342e0o2Px1");

                            context.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (l != null)
                                        l.ok(sResult.toString());
                                }
                            });


                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            l.error("发布失败");
                        }

                    }
                }.start();
            }

            @Override
            public void error(String text) {
                l.error("授权失败");
            }
        });
    }

    public static void shareToSina(final Activity context,
                                   final String shareContent,
                                   final String shareIcon,
                                   final String shareUrl,
                                   final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {
                final UMSocialService mController = SocializeConstant.getUMController();

                SinaShareContent sinaShareContent = new SinaShareContent();
                sinaShareContent.setTitle("");
                sinaShareContent.setShareContent(shareContent);
                sinaShareContent.setShareImage(new UMImage(context, shareIcon));
                sinaShareContent.setTargetUrl(shareUrl);
                mController.setShareMedia(sinaShareContent);

                mController.directShare(context, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                        if (l != null) {
                            l.ok("分享完成");
                        }
                    }
                });
            }

            @Override
            public void error(String text) {

            }
        });
    }
}
