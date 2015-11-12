package com.wan7451.socialize;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

/**
 * 社会化分享组件常量
 */
public class SocializeConstant {

    public static UMSocialService mController;

    public static UMSocialService getUMController() {
        if (mController == null)
            mController = UMServiceFactory.getUMSocialService("com.umeng.login");

        return mController;
    }


    public static final String QQ_APP_KEY = "CCC4biPJkh70bneH";
    public static final String QQ_APP_ID = "1104713556";

    public static final String WEIXIN_APP_ID = "wx956a5fb3ea62f450";
    public static final String WEIXIN_APP_SECRET = "a1f0e4a89d264bbde32d14db95f14d27";


}
