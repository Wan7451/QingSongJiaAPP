package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/9.
 */
public class YouHuiJuan {

    /**
     * campusId : 0
     * campusName :
     * create_id : 0
     * create_nm :
     * create_tm : {"date":17,"day":2,"hours":11,"minutes":11,"month":10,"nanos":0,"seconds":57,"time":1447729917000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-11-17 11:11:57
     * dri_money_amount : 200
     * dri_satisfy_amount : 1000
     * dri_use_end_dt : {"date":27,"day":5,"hours":0,"minutes":0,"month":10,"seconds":0,"time":1448553600000,"timezoneOffset":-480,"year":115}
     * dri_use_end_dt_count : 0
     * dri_use_if : 0
     * dri_user_id : 76
     * dri_user_nm : 李佳航
     * id : 1
     * isdel : 0
     * name :
     * update_id : 0
     * update_nm :
     * update_tm : {"date":17,"day":2,"hours":17,"minutes":6,"month":10,"nanos":0,"seconds":50,"time":1447751210000,"timezoneOffset":-480,"year":115}
     * update_tm_str : 2015-11-17 17:06:50
     */

    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;
    private String create_tm_str;
    private int dri_money_amount;
    private int dri_satisfy_amount;
    private int dri_use_end_dt_count;
    private int dri_use_if;
    private int dri_user_id;
    private String dri_user_nm;
    private int id;
    private int isdel;
    private String name;
    private int update_id;
    private String update_nm;
    private String update_tm_str;

    private TarenaTime dri_use_end_dt;

    private TarenaTime update_tm;

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public void setCreate_nm(String create_nm) {
        this.create_nm = create_nm;
    }

    public void setCreate_tm_str(String create_tm_str) {
        this.create_tm_str = create_tm_str;
    }

    public void setDri_money_amount(int dri_money_amount) {
        this.dri_money_amount = dri_money_amount;
    }

    public void setDri_satisfy_amount(int dri_satisfy_amount) {
        this.dri_satisfy_amount = dri_satisfy_amount;
    }

    public void setDri_use_end_dt_count(int dri_use_end_dt_count) {
        this.dri_use_end_dt_count = dri_use_end_dt_count;
    }

    public void setDri_use_if(int dri_use_if) {
        this.dri_use_if = dri_use_if;
    }

    public void setDri_user_id(int dri_user_id) {
        this.dri_user_id = dri_user_id;
    }

    public void setDri_user_nm(String dri_user_nm) {
        this.dri_user_nm = dri_user_nm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public void setUpdate_nm(String update_nm) {
        this.update_nm = update_nm;
    }

    public void setUpdate_tm_str(String update_tm_str) {
        this.update_tm_str = update_tm_str;
    }

    public int getCampusId() {
        return campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public int getCreate_id() {
        return create_id;
    }

    public String getCreate_nm() {
        return create_nm;
    }

    public String getCreate_tm_str() {
        return create_tm_str;
    }

    public int getDri_money_amount() {
        return dri_money_amount;
    }

    public int getDri_satisfy_amount() {
        return dri_satisfy_amount;
    }

    public int getDri_use_end_dt_count() {
        return dri_use_end_dt_count;
    }

    public int getDri_use_if() {
        return dri_use_if;
    }

    public int getDri_user_id() {
        return dri_user_id;
    }

    public String getDri_user_nm() {
        return dri_user_nm;
    }

    public int getId() {
        return id;
    }

    public int getIsdel() {
        return isdel;
    }

    public String getName() {
        return name;
    }

    public int getUpdate_id() {
        return update_id;
    }

    public String getUpdate_nm() {
        return update_nm;
    }

    public String getUpdate_tm_str() {
        return update_tm_str;
    }

    public void setDri_use_end_dt(TarenaTime dri_use_end_dt) {
        this.dri_use_end_dt = dri_use_end_dt;
    }

    public void setUpdate_tm(TarenaTime update_tm) {
        this.update_tm = update_tm;
    }

    public TarenaTime getDri_use_end_dt() {
        return dri_use_end_dt;
    }

    public TarenaTime getUpdate_tm() {
        return update_tm;
    }


}
