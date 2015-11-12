package com.wan7451.socialize;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.DoubanShareContent;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

/**
 * access_key=====604800
 * access_secret=====130229890
 * uid=130229890
 * location=
 * description=
 * verified=false
 * friends_count=0
 * screen_name=wg
 * statuses_count=8
 * followers_count=0
 * profile_image_url=http://img3.douban.com/pics/icon/user_icon.jpg
 * access_token=74984d2d1906a1805dc8e7874b13e480
 */
public class DouBanUtils {

    public static void auth(final Context context, final OnThirdLoginListener l) {

        ThridLogin doubanData = SocializeLocalData.getDouBanData(context);
        if (doubanData != null && l != null) {
            l.ok(doubanData);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("豆瓣登录中...");
        dialog.show();

        final ThridLogin data = new ThridLogin();

        final UMSocialService mController = SocializeConstant.getUMController();

        mController.doOauthVerify(context, SHARE_MEDIA.DOUBAN, new SocializeListeners.UMAuthListener() {
            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {

                    mController.getPlatformInfo(context, SHARE_MEDIA.DOUBAN, new SocializeListeners.UMDataListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onComplete(int status, Map<String, Object> info) {
                            dialog.dismiss();

                            if (status == 200 && info != null) {

                                data.setUid(info.get("uid").toString());
                                data.setAccess_token(info.get("access_token").toString());
                                data.setHeadimgurl(info.get("profile_image_url").toString());
                                data.setNickname(info.get("screen_name").toString());
                                data.setSex(2);

                                SocializeLocalData.saveDouBanData(context, data);
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


    public static void shareToDouBan(final Context context,
                                     final String shareContent,
                                     final String shareIcon,
                                     final String shareUrl,
                                     final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {
                final UMSocialService mController = SocializeConstant.getUMController();

                DoubanShareContent doubanShareContent = new DoubanShareContent();
                doubanShareContent.setTitle("");
                doubanShareContent.setShareContent(shareContent);
                doubanShareContent.setShareImage(new UMImage(context, shareIcon));
                doubanShareContent.setTargetUrl(shareUrl);
                mController.setShareMedia(doubanShareContent);

                mController.directShare(context, SHARE_MEDIA.DOUBAN, new SocializeListeners.SnsPostListener() {
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
