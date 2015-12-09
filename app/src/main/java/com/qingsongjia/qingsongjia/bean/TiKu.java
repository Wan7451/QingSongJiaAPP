package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/29.
 */
public enum TiKu {

    XiaoChe("xiaoche","小车", 1),
    HuoChe("houche","货车", 2),
    KeChe("keche","客车", 3),
    MotoChe("motoche","摩托车", 4),

    JiaoLianYuan("jiaolianyuan","教练员", 5),
    HuoYun("huoyun","货运", 6),
    WeiXianPin("weixianpin","危险品", 7),
    KeYun("keyun","客运", 8),
    ChuZuChe("chuzuche","出租车", 9);


    private final String value;
    private final int index;
    private final String name;

    TiKu(String value,String name, int index) {
        this.value = value;
        this.index = index;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
