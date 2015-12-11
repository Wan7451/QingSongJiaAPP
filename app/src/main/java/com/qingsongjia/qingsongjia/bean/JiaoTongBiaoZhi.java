package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/11.
 */
public class JiaoTongBiaoZhi {

    private String title;
    private int count;
    private String[] icons;

    public JiaoTongBiaoZhi(String title, int count, String[] icons) {
        this.title = title;
        this.count = count;
        this.icons = icons;
    }

    public JiaoTongBiaoZhi() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getIcons() {
        return icons;
    }

    public void setIcons(String[] icons) {
        this.icons = icons;
    }
}
