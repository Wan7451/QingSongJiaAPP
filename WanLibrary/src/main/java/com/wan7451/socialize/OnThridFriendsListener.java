package com.wan7451.socialize;

import java.util.ArrayList;

/**
 * 获取第三方好友
 */
public interface OnThridFriendsListener {

     void ok(ArrayList data, int cursor);

     void error(String text);
}
