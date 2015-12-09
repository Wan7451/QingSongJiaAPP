package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanggang on 15/12/5.
 */
public class MyYueKao implements Parcelable {
    /**
     * DIC_DRI_STATE : 3
     * campusId : 0
     * campusName :
     * create_id : 0
     * create_nm :
     * create_tm : {"date":9,"day":3,"hours":15,"minutes":24,"month":11,"nanos":0,"seconds":59,"time":1449645899000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-12-09 15:24:59
     * did : 169
     * dri_campus_id : 0
     * dri_campus_nm :
     * dri_coach_id : 127
     * dri_coach_nm : 李响
     * dri_confirm_tm : {"date":10,"day":4,"hours":0,"minutes":51,"month":11,"nanos":0,"seconds":55,"time":1449679915000,"timezoneOffset":-480,"year":115}
     * dri_confirm_tm_str : 2015-12-10 00:51:55
     * dri_dt : {"date":12,"day":6,"hours":0,"minutes":0,"month":11,"seconds":0,"time":1449849600000,"timezoneOffset":-480,"year":115}
     * dri_dt_str : 2015-12-12
     * dri_end_hm : 10
     * dri_fx_campus_id : 0
     * dri_fx_campus_nm :
     * dri_learning_content : 倒车
     * dri_plan : 231
     * dri_remark_state : 1
     * dri_remark_state_nm : 待评价
     * dri_remarks :
     * dri_start_hm : 9
     * dri_state : 2
     * dri_state_nm : 已学习
     * dri_student_id : 141
     * dri_student_nm :
     * dri_sub_nm : 2
     * dri_sub_nm_nm : 科目二
     * id : 169
     * isdel : 0
     * name :
     * type :
     * update_id : 0
     * update_nm :
     * update_tm : {"date":10,"day":4,"hours":0,"minutes":51,"month":11,"nanos":0,"seconds":55,"time":1449679915000,"timezoneOffset":-480,"year":115}
     * update_tm_str : 2015-12-10 00:51:55
     */

    //    private Integer id;//（主键）
//    private Integer dri_campus_id;//（驾校编码）
//    private String dri_campus_nm;//（驾校名称）
//    private Integer dri_fx_campus_id;//（分校编码）
//    private String dri_fx_campus_nm;//（分校名称）
//    private Integer dri_student_id;//（学员编码）
//    private String dri_student_nm;//（学员名称）
//    private String dri_sub_nm;//（科目名称）--字典项
//    private Integer dri_coach_id;//（教练id）
//    private String dri_coach_nm;//（教练名称）
//    private Date dri_dt;//（日期）
//    private Integer dri_start_hm;//（开始时间）--字典项
//    private Integer dri_end_hm;//（截止时间）--字典项
//    private String dri_state;//（学习状况）--字典项（0：“未学习”，1：“已学习”）
//    private Timestamp dri_confirm_tm;//（确认时间）
//    private Integer dri_plan; //教练课程安排ID
//    private String type;//（类型）
//    private String dri_remarks;//评价
//    private final String DIC_DRI_SUB_NM = "1";//--字典项（科目名称）
//    private final String DIC_DRI_STATE = "3";//--字典项（学习状况）

    private String DIC_DRI_STATE;
    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    private String dri_confirm_tm_str;
    private String dri_dt_str;
    private int dri_end_hm;
    private int dri_fx_campus_id;
    private String dri_fx_campus_nm;
    private String dri_learning_content;
    private int dri_plan;
    private String dri_remark_state;
    private String dri_remark_state_nm;
    private String dri_remarks;
    private int dri_start_hm;
    private String dri_state;
    private String dri_state_nm;
    private int dri_student_id;
    private String dri_student_nm;
    private String dri_sub_nm;
    private String dri_sub_nm_nm;
    private int id;
    private int isdel;
    private String name;
    private String type;
    private int update_id;
    private String update_nm;
    private String update_tm_str;

    private TarenaTime dri_dt;

    public TarenaTime getDri_dt() {
        return dri_dt;
    }

    public void setDri_dt(TarenaTime dri_dt) {
        this.dri_dt = dri_dt;
    }

    public void setDIC_DRI_STATE(String DIC_DRI_STATE) {
        this.DIC_DRI_STATE = DIC_DRI_STATE;
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

    public void setDri_confirm_tm_str(String dri_confirm_tm_str) {
        this.dri_confirm_tm_str = dri_confirm_tm_str;
    }

    public void setDri_dt_str(String dri_dt_str) {
        this.dri_dt_str = dri_dt_str;
    }

    public void setDri_end_hm(int dri_end_hm) {
        this.dri_end_hm = dri_end_hm;
    }

    public void setDri_fx_campus_id(int dri_fx_campus_id) {
        this.dri_fx_campus_id = dri_fx_campus_id;
    }

    public void setDri_fx_campus_nm(String dri_fx_campus_nm) {
        this.dri_fx_campus_nm = dri_fx_campus_nm;
    }

    public void setDri_learning_content(String dri_learning_content) {
        this.dri_learning_content = dri_learning_content;
    }

    public void setDri_plan(int dri_plan) {
        this.dri_plan = dri_plan;
    }

    public void setDri_remark_state(String dri_remark_state) {
        this.dri_remark_state = dri_remark_state;
    }

    public void setDri_remark_state_nm(String dri_remark_state_nm) {
        this.dri_remark_state_nm = dri_remark_state_nm;
    }

    public void setDri_remarks(String dri_remarks) {
        this.dri_remarks = dri_remarks;
    }

    public void setDri_start_hm(int dri_start_hm) {
        this.dri_start_hm = dri_start_hm;
    }

    public void setDri_state(String dri_state) {
        this.dri_state = dri_state;
    }

    public void setDri_state_nm(String dri_state_nm) {
        this.dri_state_nm = dri_state_nm;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDIC_DRI_STATE() {
        return DIC_DRI_STATE;
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

    public String getDri_confirm_tm_str() {
        return dri_confirm_tm_str;
    }

    public String getDri_dt_str() {
        return dri_dt_str;
    }

    public int getDri_end_hm() {
        return dri_end_hm;
    }

    public int getDri_fx_campus_id() {
        return dri_fx_campus_id;
    }

    public String getDri_fx_campus_nm() {
        return dri_fx_campus_nm;
    }

    public String getDri_learning_content() {
        return dri_learning_content;
    }

    public int getDri_plan() {
        return dri_plan;
    }

    public String getDri_remark_state() {
        return dri_remark_state;
    }

    public String getDri_remark_state_nm() {
        return dri_remark_state_nm;
    }

    public String getDri_remarks() {
        return dri_remarks;
    }

    public int getDri_start_hm() {
        return dri_start_hm;
    }

    public String getDri_state() {
        return dri_state;
    }

    public String getDri_state_nm() {
        return dri_state_nm;
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

    public int getId() {
        return id;
    }

    public int getIsdel() {
        return isdel;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DIC_DRI_STATE);
        dest.writeInt(this.campusId);
        dest.writeString(this.campusName);
        dest.writeInt(this.create_id);
        dest.writeString(this.create_nm);
        dest.writeString(this.create_tm_str);
        dest.writeInt(this.did);
        dest.writeInt(this.dri_campus_id);
        dest.writeString(this.dri_campus_nm);
        dest.writeInt(this.dri_coach_id);
        dest.writeString(this.dri_coach_nm);
        dest.writeString(this.dri_confirm_tm_str);
        dest.writeString(this.dri_dt_str);
        dest.writeInt(this.dri_end_hm);
        dest.writeInt(this.dri_fx_campus_id);
        dest.writeString(this.dri_fx_campus_nm);
        dest.writeString(this.dri_learning_content);
        dest.writeInt(this.dri_plan);
        dest.writeString(this.dri_remark_state);
        dest.writeString(this.dri_remark_state_nm);
        dest.writeString(this.dri_remarks);
        dest.writeInt(this.dri_start_hm);
        dest.writeString(this.dri_state);
        dest.writeString(this.dri_state_nm);
        dest.writeInt(this.dri_student_id);
        dest.writeString(this.dri_student_nm);
        dest.writeString(this.dri_sub_nm);
        dest.writeString(this.dri_sub_nm_nm);
        dest.writeInt(this.id);
        dest.writeInt(this.isdel);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeInt(this.update_id);
        dest.writeString(this.update_nm);
        dest.writeString(this.update_tm_str);
        dest.writeParcelable(this.dri_dt, flags);
    }

    public MyYueKao() {
    }

    protected MyYueKao(Parcel in) {
        this.DIC_DRI_STATE = in.readString();
        this.campusId = in.readInt();
        this.campusName = in.readString();
        this.create_id = in.readInt();
        this.create_nm = in.readString();
        this.create_tm_str = in.readString();
        this.did = in.readInt();
        this.dri_campus_id = in.readInt();
        this.dri_campus_nm = in.readString();
        this.dri_coach_id = in.readInt();
        this.dri_coach_nm = in.readString();
        this.dri_confirm_tm_str = in.readString();
        this.dri_dt_str = in.readString();
        this.dri_end_hm = in.readInt();
        this.dri_fx_campus_id = in.readInt();
        this.dri_fx_campus_nm = in.readString();
        this.dri_learning_content = in.readString();
        this.dri_plan = in.readInt();
        this.dri_remark_state = in.readString();
        this.dri_remark_state_nm = in.readString();
        this.dri_remarks = in.readString();
        this.dri_start_hm = in.readInt();
        this.dri_state = in.readString();
        this.dri_state_nm = in.readString();
        this.dri_student_id = in.readInt();
        this.dri_student_nm = in.readString();
        this.dri_sub_nm = in.readString();
        this.dri_sub_nm_nm = in.readString();
        this.id = in.readInt();
        this.isdel = in.readInt();
        this.name = in.readString();
        this.type = in.readString();
        this.update_id = in.readInt();
        this.update_nm = in.readString();
        this.update_tm_str = in.readString();
        this.dri_dt = in.readParcelable(TarenaTime.class.getClassLoader());
    }

    public static final Parcelable.Creator<MyYueKao> CREATOR = new Parcelable.Creator<MyYueKao>() {
        public MyYueKao createFromParcel(Parcel source) {
            return new MyYueKao(source);
        }

        public MyYueKao[] newArray(int size) {
            return new MyYueKao[size];
        }
    };
}
