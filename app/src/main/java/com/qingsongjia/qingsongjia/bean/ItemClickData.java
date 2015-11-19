package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/19.
 * 左边图片，中间文字，右边文字、箭头
 */
public class ItemClickData {

    private int icon;
    private String mainText;  //中间文字
    private String secondText;  //右边文字
    private boolean isShowArrow = true;  //是否显示箭头

    public ItemClickData(int icon, String mainText, String secondText, boolean isShowArrow) {
        this.icon = icon;
        this.mainText = mainText;
        this.secondText = secondText;
        this.isShowArrow = isShowArrow;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

    public boolean isShowArrow() {
        return isShowArrow;
    }

    public void setIsShowArrow(boolean isShowArrow) {
        this.isShowArrow = isShowArrow;
    }
}
