package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/29.
 */
public enum TiKu {

    XiaoChe("xiaoche", 1),
    HuoChe("houche", 2),
    KeChe("keche", 3),
    MotoChe("motoche", 4),

    JiaoLianYuan("jiaolianyuan", 5),
    HuoYun("huoyun", 6),
    WeiXianPin("weixianpin", 7),
    KeYun("keyun", 8),
    ChuZuChe("chuzuche", 9);


    private final String value;
    private final int index;

    TiKu(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
