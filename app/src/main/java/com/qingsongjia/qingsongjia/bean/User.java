package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/2.
 */
public class User {

    /**
     * did : 112  （主键）
     * dri_campus_id : 0
     * dri_campus_nm :
     * dri_nm :
     * dri_unm : 13716458664
     * dri_upwd : wg
     * id : 112
     private Integer id;//（主键）
     private Integer dri_campus_id;//（驾校）
     private String dri_campus_nm;//（驾校名称）
     private String dri_nm;//（姓名）
     private String dri_unm;//（账户名）
     private String dri_upwd;//（账号密码）
     */



    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private String dri_nm;
    private String dri_unm;
    private String dri_upwd;
    private int id;

    private String dri_type;


    public void setDid(int did) {
        this.did = did;
    }

    public void setDri_campus_id(int dri_campus_id) {
        this.dri_campus_id = dri_campus_id;
    }

    public void setDri_campus_nm(String dri_campus_nm) {
        this.dri_campus_nm = dri_campus_nm;
    }

    public void setDri_nm(String dri_nm) {
        this.dri_nm = dri_nm;
    }

    public void setDri_unm(String dri_unm) {
        this.dri_unm = dri_unm;
    }

    public void setDri_upwd(String dri_upwd) {
        this.dri_upwd = dri_upwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDid() {
        return did;
    }

    public int getDri_campus_id() {
        return dri_campus_id;
    }

    public String getDri_campus_nm() {
        return dri_campus_nm;
    }

    public String getDri_nm() {
        return dri_nm;
    }

    public String getDri_unm() {
        return dri_unm;
    }

    public String getDri_upwd() {
        return dri_upwd;
    }

    public int getId() {
        return id;
    }

    public void setDri_type(String dri_type) {
        this.dri_type = dri_type;
    }

    public String getDri_type() {
        return dri_type;
    }
}
