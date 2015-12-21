package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/5.
 */
public class MyKaoShi {

    //    private Integer id;//（主键）
//    private Integer dri_campus_id;//（驾校编码）
//    private String dri_campus_nm;//（驾校名称）
//    private Integer dri_fx_campus_id;//（分校编码）
//    private String dri_fx_campus_nm;//（分校名称）
//    private Integer dri_student_id;//（学员编码）
//    private String dri_student_nm;//（学员名称）
//    private String dri_sub_nm;//（科目名称）--字典项
//    private Date dri_dt;//（约考日期）
//    private String dri_tm;//（约考时间）
//    private Integer dri_score;//（成绩）
//    private String dri_result;//（结果）
//    private Date dri_exam_dt;//（考试日期）
//    private String dri_exam_tm;//（考试时间）--字典项
//    private String dri_dt_str;
//    private String dri_exam_dt_str;
//    private Integer dri_coach_id;//约考教练id
//    private String dri_coach_nm;//约考教练姓名

    /**
     * create_id : 0
     * create_nm :
     * create_tm : {"date":13,"day":0,"hours":20,"minutes":19,"month":11,"nanos":0,"seconds":33,"time":1450009173000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-12-13 20:19:33
     * did : 196
     * dri_campus_id : 291
     * dri_campus_nm : 英明驾校
     * dri_coach_id : 153
     * dri_coach_nm : 刘飞
     * dri_comment :
     * dri_dt : null
     * dri_dt_str :
     * dri_exam_dt : null
     * dri_exam_dt_str :
     * dri_exam_tm : 上午
     * dri_fx_campus_id : 0
     * dri_fx_campus_nm :
     * dri_result : 通过
     * dri_score : 80
     * dri_student_id : 0
     * dri_student_nm : 王刚
     * dri_sub_nm : 3
     * dri_sub_nm_nm : 科目三
     * dri_tm : 1
     * id : 196
     * isdel : 0
     * name :
     * update_id : 0
     * update_nm :
     * update_tm : {"date":13,"day":0,"hours":20,"minutes":19,"month":11,"nanos":0,"seconds":33,"time":1450009173000,"timezoneOffset":-480,"year":115}
     * update_tm_str : 2015-12-13 20:19:33
     */

    private int create_id;
    private String create_nm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    private String dri_comment;
    private TarenaTime dri_dt;
    private String dri_dt_str;
    private TarenaTime dri_exam_dt;
    private String dri_exam_dt_str;
    private String dri_exam_tm;
    private int dri_fx_campus_id;
    private String dri_fx_campus_nm;
    private String dri_result;
    private int dri_score;
    private int dri_student_id;
    private String dri_student_nm;
    private String dri_sub_nm;
    private String dri_sub_nm_nm;
    private String dri_tm;
    private int id;
    private int isdel;
    private String name;
    private int update_id;
    private String update_nm;
    private String update_tm_str;
    private TarenaTime create_tm;
    private TarenaTime update_tm;


    public TarenaTime getCreate_tm() {
        return create_tm;
    }

    public void setCreate_tm(TarenaTime create_tm) {
        this.create_tm = create_tm;
    }

    public TarenaTime getUpdate_tm() {
        return update_tm;
    }

    public void setUpdate_tm(TarenaTime update_tm) {
        this.update_tm = update_tm;
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

    public void setDri_comment(String dri_comment) {
        this.dri_comment = dri_comment;
    }

    public void setDri_dt(TarenaTime dri_dt) {
        this.dri_dt = dri_dt;
    }

    public void setDri_dt_str(String dri_dt_str) {
        this.dri_dt_str = dri_dt_str;
    }

    public void setDri_exam_dt(TarenaTime dri_exam_dt) {
        this.dri_exam_dt = dri_exam_dt;
    }

    public void setDri_exam_dt_str(String dri_exam_dt_str) {
        this.dri_exam_dt_str = dri_exam_dt_str;
    }

    public void setDri_exam_tm(String dri_exam_tm) {
        this.dri_exam_tm = dri_exam_tm;
    }

    public void setDri_fx_campus_id(int dri_fx_campus_id) {
        this.dri_fx_campus_id = dri_fx_campus_id;
    }

    public void setDri_fx_campus_nm(String dri_fx_campus_nm) {
        this.dri_fx_campus_nm = dri_fx_campus_nm;
    }

    public void setDri_result(String dri_result) {
        this.dri_result = dri_result;
    }

    public void setDri_score(int dri_score) {
        this.dri_score = dri_score;
    }

    public void setDri_student_id(int dri_student_id) {
        this.dri_student_id = dri_student_id;
    }

    public void setDri_student_nm(String dri_student_nm) {
        this.dri_student_nm = dri_student_nm;
    }

    public void setDri_sub_nm(String dri_sub_nm) {
        this.dri_sub_nm = dri_sub_nm;
    }

    public void setDri_sub_nm_nm(String dri_sub_nm_nm) {
        this.dri_sub_nm_nm = dri_sub_nm_nm;
    }

    public void setDri_tm(String dri_tm) {
        this.dri_tm = dri_tm;
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

    public String getDri_comment() {
        return dri_comment;
    }

    public Object getDri_dt() {
        return dri_dt;
    }

    public String getDri_dt_str() {
        return dri_dt_str;
    }

    public Object getDri_exam_dt() {
        return dri_exam_dt;
    }

    public String getDri_exam_dt_str() {
        return dri_exam_dt_str;
    }

    public String getDri_exam_tm() {
        return dri_exam_tm;
    }

    public int getDri_fx_campus_id() {
        return dri_fx_campus_id;
    }

    public String getDri_fx_campus_nm() {
        return dri_fx_campus_nm;
    }

    public String getDri_result() {
        return dri_result;
    }

    public int getDri_score() {
        return dri_score;
    }

    public int getDri_student_id() {
        return dri_student_id;
    }

    public String getDri_student_nm() {
        return dri_student_nm;
    }

    public String getDri_sub_nm() {
        return dri_sub_nm;
    }

    public String getDri_sub_nm_nm() {
        return dri_sub_nm_nm;
    }

    public String getDri_tm() {
        return dri_tm;
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

}
