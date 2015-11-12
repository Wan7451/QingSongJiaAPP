package com.wan7451.socialize;

/**
 * 第三方登录
 */
public interface OnThirdLoginListener {

     void ok(ThridLogin data);

     void error(String text);
}
