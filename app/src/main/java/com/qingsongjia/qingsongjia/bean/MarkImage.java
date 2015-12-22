package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/22.
 */
public class MarkImage {

    /**
     * title : 禁止二轮摩托车驶入
     * imageurl : 89
     * desc : 表示禁止两轮摩托车驶入。此标志设在禁止两轮摩托车驶入的路段入口处。
     */

    private String title;
    private String imageurl;
    private String desc;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getDesc() {
        return desc;
    }
}
