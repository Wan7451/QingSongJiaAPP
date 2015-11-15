package com.qingsongjia.qingsongjia.bean;

/**
 * 上图下字Bean
 * Created by wanggang on 15/11/15.
 */
public class ExamImageText {

    private String showText;
    private int  showImageRes;

    public ExamImageText() {
    }

    public ExamImageText(int showImageRes,String showText) {
        this.showText = showText;
        this.showImageRes = showImageRes;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public int getShowImageRes() {
        return showImageRes;
    }

    public void setShowImageRes(int showImageRes) {
        this.showImageRes = showImageRes;
    }
}
