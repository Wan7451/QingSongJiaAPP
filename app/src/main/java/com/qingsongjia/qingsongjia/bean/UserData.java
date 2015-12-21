package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/5.
 */
public class UserData {

//    private Integer did; // 账户ID
//    private Integer dri_campus_id; // 驾校ID
//    private String dri_campus_nm; // 驾校名称
//    private String dri_car_type; // 学车类型
//    private String dri_car_type_nm; // 学车类型名字
//    private Integer dri_coach_id; // 教练ID
//    private String dri_coach_nm; // 教练名字
//    private String dri_file_path; // 账户头像
//    private String dri_nm; // 账户姓名
//    private String dri_tel; // 账户电话

    /**
     * did : 112
     * dri_campus_id : 0
     * dri_campus_nm :
     * dri_coach_id : 153
     * dri_coach_nm : 刘飞
     * dri_nm :
     * dri_tel :
     * dri_type :
     * dri_unm : 13716458664
     * dri_upwd : wg
     * id : 112
     */

    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    private String dri_nm;
    private String dri_tel;
    private String dri_type;
    private String dri_unm;
    private String dri_upwd;
    private int id;
    /**
     * dri_car_type :
     * dri_car_type_nm :
     * dri_file_path : http://7xlt5l.com1.z0.glb.clouddn.com/1450590956126icon.jpg
     */

    private String dri_car_type;
    private String dri_car_type_nm;
    private String dri_file_path;

    public void setDid(int did) {
        this.did = did;
    }

    public void setDri_campus_id(int dri_campus_id) {
        this.dri_campus_id = dri_campus_id;
    }

    public void setDri_campus_nm(String dri_campus_nm) {
        this.dri_campus_nm = dri_campus_nm;
    }

    public void setDri_coach_id(int dri_coach_id) {
        this.dri_coach_id = dri_coach_id;
    }

    public void setDri_coach_nm(String dri_coach_nm) {
        this.dri_coach_nm = dri_coach_nm;
    }

    public void setDri_nm(String dri_nm) {
        this.dri_nm = dri_nm;
    }

    public void setDri_tel(String dri_tel) {
        this.dri_tel = dri_tel;
    }

    public void setDri_type(String dri_type) {
        this.dri_type = dri_type;
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

    public int getDri_coach_id() {
        return dri_coach_id;
    }

    public String getDri_coach_nm() {
        return dri_coach_nm;
    }

    public String getDri_nm() {
        return dri_nm;
    }

    public String getDri_tel() {
        return dri_tel;
    }

    public String getDri_type() {
        return dri_type;
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

    public void setDri_car_type(String dri_car_type) {
        this.dri_car_type = dri_car_type;
    }

    public void setDri_car_type_nm(String dri_car_type_nm) {
        this.dri_car_type_nm = dri_car_type_nm;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public String getDri_car_type() {
        return dri_car_type;
    }

    public String getDri_car_type_nm() {
        return dri_car_type_nm;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }
}
