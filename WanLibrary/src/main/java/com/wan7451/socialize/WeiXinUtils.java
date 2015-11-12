package com.wan7451.socialize;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/22.
 * <p>
 * <p>
 * access_token=====OezXcEiiBSKSxW0eoylIeGEi1IquChziYo8qebwRD7Tfbjr0viBPCX5MEo1QqxRjQzkUzR5H5svQ2HrIZi6o67lQwdmOrh-jVXGQb-Hyr1XIGWq2g_d-6RXG5Ygw4IhxSWqDC69Rl8Eg8oPf1IhpjQ
 * openid=====7200
 * refresh_token_expires=====obFcruDzqk41NDuOsKL_NCi6mUNY
 * uid=====snsapi_userinfo
 * sex=1
 * nickname=王刚
 * unionid=obFcruDzqk41NDuOsKL_NCi6mUNY
 * province=北京
 * openid=oe1J6uEfkiPRT838uj4z7QGalpYU
 * language=zh_CN
 * headimgurl=http://wx.qlogo.cn/mmopen/ajNVdqHZLLA2rj2HytElCLicDNQNdzcaIf5QJ1iaicDRHO9Tzibmibd2slQFZfSIIDzWnhunDK40hGntQsxXd31cnnw/0
 * country=中国
 * city=海淀
 */
public class WeiXinUtils {

    public static void auth(final Activity context, final OnThirdLoginListener l) {

        ThridLogin weixinData = SocializeLocalData.getWeiXinData(context);
        if (weixinData != null && l != null) {
            l.ok(weixinData);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("微信登录中...");
        dialog.show();

        final ThridLogin data = new ThridLogin();

        final UMSocialService mController = SocializeConstant.getUMController();

        UMWXHandler wxHandler = new UMWXHandler(context, SocializeConstant.WEIXIN_APP_ID, SocializeConstant.WEIXIN_APP_SECRET);
        wxHandler.addToSocialSDK();

        mController.doOauthVerify(context, SHARE_MEDIA.WEIXIN, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(context, "授权错误", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {

                data.setAccess_token(value.getString("access_token"));

                //获取相关授权信息
                mController.getPlatformInfo(context, SHARE_MEDIA.WEIXIN, new SocializeListeners.UMDataListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {

                        dialog.dismiss();
                        if (status == 200 && info != null) {

                            data.setNickname(info.get("nickname").toString());
                            data.setUid(info.get("openid").toString());
                            data.setHeadimgurl(info.get("headimgurl").toString());
                            data.setSex((Integer) info.get("sex"));

                            SocializeLocalData.saveWeiXinData(context, data);
                            if (l != null) {
                                l.ok(data);
                            }
                        } else {
                            Toast.makeText(context, "发生错误：" + status, Toast.LENGTH_SHORT).show();
                            if (l != null) {
                                l.error("发生错误：" + status);
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                dialog.dismiss();
            }
        });

    }


    public static void shartToWeixin(final Activity context,
                                     final String shareContent,
                                     final String shareIcon,
                                     final String title,
                                     final String shareUrl,
                                     final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {
                final UMSocialService mController = SocializeConstant.getUMController();
                UMWXHandler wxHandler = new UMWXHandler(context, SocializeConstant.WEIXIN_APP_ID, SocializeConstant.WEIXIN_APP_SECRET);
                wxHandler.addToSocialSDK();

                WeiXinShareContent weixinContent = new WeiXinShareContent();
                weixinContent.setShareContent(shareContent);
                weixinContent.setTitle(title);
                weixinContent.setTargetUrl(shareUrl);
                weixinContent.setShareImage(new UMImage(context, shareIcon));
                mController.setShareMedia(weixinContent);

                mController.directShare(context, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener() {
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

    public static void shartToWeixinCircle(final Activity context,
                                           final String shareContent,
                                           final String shareIcon,
                                           final String shareUrl,
                                           final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {
                final UMSocialService mController = SocializeConstant.getUMController();

                UMWXHandler wxCircleHandler = new UMWXHandler(context, SocializeConstant.WEIXIN_APP_ID, SocializeConstant.WEIXIN_APP_SECRET);
                wxCircleHandler.setToCircle(true);
                wxCircleHandler.addToSocialSDK();

                CircleShareContent circleMedia = new CircleShareContent();
                circleMedia.setShareContent(shareContent);
                circleMedia.setTitle(shareContent);
                circleMedia.setShareImage(new UMImage(context, shareIcon));
                circleMedia.setTargetUrl(shareUrl);
                mController.setShareMedia(circleMedia);

                mController.directShare(context, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener() {
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
