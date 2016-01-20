package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/16.
 */
public class SchoolEvaluate {


    /**
     * campusId : 250
     * campusName :
     * create_id : 112
     * create_nm : 王刚
     * create_tm : {"date":3,"day":4,"hours":17,"minutes":7,"month":11,"nanos":0,"seconds":36,"time":1449133656000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-12-03 17:07:36
     * did : 141
     * dri_file_path : http://7xlt5l.com1.z0.glb.clouddn.com/1449734769519ohgmmj3048628
     * dri_pass : 2
     * dri_place : 1
     * dri_praiass : 2
     * dri_place : 1
     * dri_praise_num : 0se_num : 0
     * dri_remark : 还是很不错的
     * dri_time : 3
     * id : 141
     * isdel : 0
     * name :
     * update_id : 0
     * update_nm :
     * update_tm : {"date":3,"day":4,"hours":17,"minutes":7,"month":11,"nanos":0,"seconds":36,"time":1449133656000,"timezoneOffset":-480,"year":115}
     * update_tm_str : 2015-12-03 17:07:36
     */



    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;
    private String create_tm_str;
    private int did;
    private String dri_file_path;
    private int dri_pass;
    private int dri_place;
    private int dri_praise_num;
    private String dri_remark;
    private int dri_time;
    private int id;
    private int isdel;
    private String name;
    private int update_id;
    private String update_nm;
    private String update_tm_str;
    private TarenaTime update_tm;
    private TarenaTime create_tm;

    /**
     * dri_is_like : 0
     * dri_like_count : 0
     * registFees : 0
     */

    private int dri_is_like;
    private int dri_like_count;
    private int registFees;


    public TarenaTime getUpdate_tm() {
        return update_tm;
    }

    public void setUpdate_tm(TarenaTime update_tm) {
        this.update_tm = update_tm;
    }

    public TarenaTime getCreate_tm() {
        return create_tm;
    }

    public void setCreate_tm(TarenaTime create_tm) {
        this.create_tm = create_tm;
    }

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

    public void setDid(int did) {
        this.did = did;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public void setDri_pass(int dri_pass) {
        this.dri_pass = dri_pass;
    }

    public void setDri_place(int dri_place) {
        this.dri_place = dri_place;
    }

    public void setDri_praise_num(int dri_praise_num) {
        this.dri_praise_num = dri_praise_num;
    }

    public void setDri_remark(String dri_remark) {
        this.dri_remark = dri_remark;
    }

    public void setDri_time(int dri_time) {
        this.dri_time = dri_time;
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

    public int getDid() {
        return did;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }

    public int getDri_pass() {
        return dri_pass;
    }

    public int getDri_place() {
        return dri_place;
    }

    public int getDri_praise_num() {
        return dri_praise_num;
    }

    public String getDri_remark() {
        return dri_remark;
    }

    public int getDri_time() {
        return dri_time;
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

    public void setDri_is_like(int dri_is_like) {
        this.dri_is_like = dri_is_like;
    }

    public void setDri_like_count(int dri_like_count) {
        this.dri_like_count = dri_like_count;
    }

    public void setRegistFees(int registFees) {
        this.registFees = registFees;
    }

    public int getDri_is_like() {
        return dri_is_like;
    }

    public int getDri_like_count() {
        return dri_like_count;
    }

    public int getRegistFees() {
        return registFees;
    }
}
