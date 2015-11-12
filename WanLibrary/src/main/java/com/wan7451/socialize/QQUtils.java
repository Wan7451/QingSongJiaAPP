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
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/22.
 * access_token=====6EAB3E88FAAFA5C31C33EC109814DA40
 * expires_in=====4736382CC85152918CB3C002C4736432
 * pf=====0
 * uid=====
 * appid=====db251e167ada6e03fc2f38771dba60c3
 * page_type=====
 * is_yellow_year_vip=0
 * <p>
 * vip=0
 * level=0
 * province=山西
 * yellow_vip_level=0
 * is_yellow_vip=0
 * gender=男
 * screen_name=向前走，无所畏
 * msg=
 * profile_image_url=http://q.qlogo.cn/qqapp/1104713556/6EAB3E88FAAFA5C31C33EC109814DA40/100
 * city=太原
 */
public class QQUtils {

    public static void auth(final Activity context, final OnThirdLoginListener l) {

        ThridLogin qqData = SocializeLocalData.getQQData(context);
        if (qqData != null && l != null) {
            l.ok(qqData);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("QQ登录中...");
        dialog.show();
        final ThridLogin data = new ThridLogin();

        final UMSocialService mController = SocializeConstant.getUMController();

        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(context, SocializeConstant.QQ_APP_ID,
                SocializeConstant.QQ_APP_KEY);
        qqSsoHandler.addToSocialSDK();

        mController.doOauthVerify(context, SHARE_MEDIA.QQ, new SocializeListeners.UMAuthListener() {
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
                data.setUid(value.getString("uid"));

                //获取相关授权信息
                mController.getPlatformInfo(context, SHARE_MEDIA.QQ, new SocializeListeners.UMDataListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {

                        dialog.dismiss();

                        if (status == 200 && info != null) {

                            String gender = info.get("gender").toString();
                            if (gender.equals("男"))
                                data.setSex(1);
                            else
                                data.setSex(2);

                            data.setNickname(info.get("screen_name").toString());
                            data.setHeadimgurl(info.get("profile_image_url").toString());

                            SocializeLocalData.saveQQData(context, data);

                            if (l != null)
                                l.ok(data);
                        } else {
                            if (l != null)
                                l.error("发生错误：" + status);
                            Toast.makeText(context, "发生错误：" + status, Toast.LENGTH_SHORT).show();
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


    public static void shareToQQ(final Activity context,
                                 final String shareContent,
                                 final String shareUrl,
                                 final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {

                final UMSocialService mController = SocializeConstant.getUMController();

                UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(context, SocializeConstant.QQ_APP_ID,
                        SocializeConstant.QQ_APP_KEY);
                qqSsoHandler.addToSocialSDK();

                QQShareContent qqShareContent = new QQShareContent();
                qqShareContent.setTitle(shareContent);
//                qqShareContent.setShareImage(new UMImage(context,SHARE_ICON));
                qqShareContent.setTargetUrl(shareUrl);
                mController.setShareMedia(qqShareContent);

                mController.directShare(context, SHARE_MEDIA.QQ, new SocializeListeners.SnsPostListener() {
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

    public static void shareToQZone(final Activity context,
                                    final String shareTitle,
                                    final String shareContent,
                                    final String shareIcon,
                                    final String shareUrl,
                                    final OnThirdShareListener l) {
        auth(context, new OnThirdLoginListener() {
            @Override
            public void ok(ThridLogin data) {

                final UMSocialService mController = SocializeConstant.getUMController();


                QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(context, SocializeConstant.QQ_APP_ID,
                        SocializeConstant.QQ_APP_KEY);
                qZoneSsoHandler.addToSocialSDK();

                QZoneShareContent qzone = new QZoneShareContent();
                qzone.setShareContent(shareContent);
                qzone.setTargetUrl(shareUrl);
                qzone.setTitle(shareTitle);
                qzone.setShareImage(new UMImage(context, shareIcon));
                mController.setShareMedia(qzone);

                mController.directShare(context, SHARE_MEDIA.QZONE, new SocializeListeners.SnsPostListener() {
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
