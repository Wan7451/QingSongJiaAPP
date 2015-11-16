package com.wan7451.radiamenu;

import java.util.List;

/**
 * Created by wanggang on 15/11/15.
 */
public interface RadialMenuInterface {
    String getName();
    String getLabel();
    int getIcon();
    List<RadialMenuItem> getChildren();
    void menuActiviated();
}
