package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanggang on 15/12/6.
 */
public class PeiLian implements Parcelable {

    /**
     * carType :
     * contactName :
     * create_id : 129
     * create_tm : {"date":16,"day":6,"hours":11,"minutes":0,"month":0,"nanos":0,"seconds":7,"time":1452913207000,"timezoneOffset":-480,"year":116}
     * did : 178
     * dri_coach_id : 0
     * dri_coach_nm :
     * dri_comments : 备注
     * dri_file_path : http://7xlt5l.com1.z0.glb.clouddn.com/1451459924479icon.jpg
     * dri_grab_date : null
     * dri_grab_date_str :
     * dri_partner_type : 1
     * dri_partner_type_nm : 定点上车
     * dri_price : 119
     * dri_remark :
     * dri_remark_state : 1
     * dri_remark_state_nm : 待评价
     * dri_remark_state_two : 1
     * dri_remark_two :
     * dri_user_id : 129
     * dri_user_nm : 我的的
     * id : 178
     * isdel : 0
     * meetingDate : {"date":16,"day":6,"hours":0,"minutes":0,"month":0,"seconds":0,"time":1452873600000,"timezoneOffset":-480,"year":116}
     * meetingDate_str : 2016-01-16
     * meetingTime : 11
     * status : 1
     * telephoneNumber :
     * update_id : 129
     * update_tm : {"date":16,"day":6,"hours":11,"minutes":0,"month":0,"nanos":0,"seconds":7,"time":1452913207000,"timezoneOffset":-480,"year":116}
     * validateCode :
     */

    private String carType;
    private String contactName;
    private int create_id;
    private int did;
    private int dri_coach_id;

    private String dri_comments;

    private TarenaTime dri_grab_date;
    private String dri_grab_date_str;
    private String dri_partner_type;
    private String dri_partner_type_nm;
    private int dri_price;
    private String dri_remark;
    private int dri_remark_state;
    private String dri_remark_state_nm;
    private int dri_remark_state_two;
    private String dri_remark_two;
    private int dri_user_id;

    private int id;
    private String isdel;
    private String meetingDate_str;
    private int meetingTime;
    private int status;
    private String telephoneNumber;
    private int update_id;
    private String validateCode;

    private String dri_consult_file_path;//学员头像
    private String dri_coach_tel;//学员电话
    private String dri_coach_nm; //学员名字

    private String dri_user_tel;//教练电话
    private String dri_user_nm; //教练名字
    private String dri_file_path;//教练头像

    public String getDri_consult_file_path() {
        return dri_consult_file_path;
    }

    public void setDri_consult_file_path(String dri_consult_file_path) {
        this.dri_consult_file_path = dri_consult_file_path;
    }

    public String getDri_user_tel() {
        return dri_user_tel;
    }

    public void setDri_user_tel(String dri_user_tel) {
        this.dri_user_tel = dri_user_tel;
    }

    public String getDri_coach_tel() {
        return dri_coach_tel;
    }

    public void setDri_coach_tel(String dri_coach_tel) {
        this.dri_coach_tel = dri_coach_tel;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public void setDri_coach_id(int dri_coach_id) {
        this.dri_coach_id = dri_coach_id;
    }

    public void setDri_coach_nm(String dri_coach_nm) {
        this.dri_coach_nm = dri_coach_nm;
    }

    public void setDri_comments(String dri_comments) {
        this.dri_comments = dri_comments;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public void setDri_grab_date(TarenaTime dri_grab_date) {
        this.dri_grab_date = dri_grab_date;
    }

    public void setDri_grab_date_str(String dri_grab_date_str) {
        this.dri_grab_date_str = dri_grab_date_str;
    }

    public void setDri_partner_type(String dri_partner_type) {
        this.dri_partner_type = dri_partner_type;
    }

    public void setDri_partner_type_nm(String dri_partner_type_nm) {
        this.dri_partner_type_nm = dri_partner_type_nm;
    }

    public void setDri_price(int dri_price) {
        this.dri_price = dri_price;
    }

    public void setDri_remark(String dri_remark) {
        this.dri_remark = dri_remark;
    }

    public void setDri_remark_state(int dri_remark_state) {
        this.dri_remark_state = dri_remark_state;
    }

    public void setDri_remark_state_nm(String dri_remark_state_nm) {
        this.dri_remark_state_nm = dri_remark_state_nm;
    }

    public void setDri_remark_state_two(int dri_remark_state_two) {
        this.dri_remark_state_two = dri_remark_state_two;
    }

    public void setDri_remark_two(String dri_remark_two) {
        this.dri_remark_two = dri_remark_two;
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

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public void setMeetingDate_str(String meetingDate_str) {
        this.meetingDate_str = meetingDate_str;
    }

    public void setMeetingTime(int meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getCarType() {
        return carType;
    }

    public String getContactName() {
        return contactName;
    }

    public int getCreate_id() {
        return create_id;
    }

    public int getDid() {
        return did;
    }

    public int getDri_coach_id() {
        return dri_coach_id;
    }

    public String getDri_coach_nm() {
        return dri_coach_nm;
    }

    public String getDri_comments() {
        return dri_comments;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }

    public Object getDri_grab_date() {
        return dri_grab_date;
    }

    public String getDri_grab_date_str() {
        return dri_grab_date_str;
    }

    public String getDri_partner_type() {
        return dri_partner_type;
    }

    public String getDri_partner_type_nm() {
        return dri_partner_type_nm;
    }

    public int getDri_price() {
        return dri_price;
    }

    public String getDri_remark() {
        return dri_remark;
    }

    public int getDri_remark_state() {
        return dri_remark_state;
    }

    public String getDri_remark_state_nm() {
        return dri_remark_state_nm;
    }

    public int getDri_remark_state_two() {
        return dri_remark_state_two;
    }

    public String getDri_remark_two() {
        return dri_remark_two;
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

    public String getIsdel() {
        return isdel;
    }

    public String getMeetingDate_str() {
        return meetingDate_str;
    }

    public int getMeetingTime() {
        return meetingTime;
    }

    public int getStatus() {
        return status;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getUpdate_id() {
        return update_id;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public PeiLian() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carType);
        dest.writeString(this.contactName);
        dest.writeInt(this.create_id);
        dest.writeInt(this.did);
        dest.writeInt(this.dri_coach_id);
        dest.writeString(this.dri_coach_nm);
        dest.writeString(this.dri_comments);
        dest.writeString(this.dri_file_path);
        dest.writeParcelable(this.dri_grab_date, 0);
        dest.writeString(this.dri_grab_date_str);
        dest.writeString(this.dri_partner_type);
        dest.writeString(this.dri_partner_type_nm);
        dest.writeInt(this.dri_price);
        dest.writeString(this.dri_remark);
        dest.writeInt(this.dri_remark_state);
        dest.writeString(this.dri_remark_state_nm);
        dest.writeInt(this.dri_remark_state_two);
        dest.writeString(this.dri_remark_two);
        dest.writeInt(this.dri_user_id);
        dest.writeInt(this.id);
        dest.writeString(this.isdel);
        dest.writeString(this.meetingDate_str);
        dest.writeInt(this.meetingTime);
        dest.writeInt(this.status);
        dest.writeString(this.telephoneNumber);
        dest.writeInt(this.update_id);
        dest.writeString(this.validateCode);
        dest.writeString(this.dri_consult_file_path);
        dest.writeString(this.dri_user_tel);
        dest.writeString(this.dri_coach_tel);
        dest.writeString(this.dri_user_nm);
    }

    protected PeiLian(Parcel in) {
        this.carType = in.readString();
        this.contactName = in.readString();
        this.create_id = in.readInt();
        this.did = in.readInt();
        this.dri_coach_id = in.readInt();
        this.dri_coach_nm = in.readString();
        this.dri_comments = in.readString();
        this.dri_file_path = in.readString();
        this.dri_grab_date = in.readParcelable(TarenaTime.class.getClassLoader());
        this.dri_grab_date_str = in.readString();
        this.dri_partner_type = in.readString();
        this.dri_partner_type_nm = in.readString();
        this.dri_price = in.readInt();
        this.dri_remark = in.readString();
        this.dri_remark_state = in.readInt();
        this.dri_remark_state_nm = in.readString();
        this.dri_remark_state_two = in.readInt();
        this.dri_remark_two = in.readString();
        this.dri_user_id = in.readInt();
        this.id = in.readInt();
        this.isdel = in.readString();
        this.meetingDate_str = in.readString();
        this.meetingTime = in.readInt();
        this.status = in.readInt();
        this.telephoneNumber = in.readString();
        this.update_id = in.readInt();
        this.validateCode = in.readString();
        this.dri_consult_file_path = in.readString();
        this.dri_user_tel = in.readString();
        this.dri_coach_tel = in.readString();
        this.dri_user_nm = in.readString();
    }

    public static final Creator<PeiLian> CREATOR = new Creator<PeiLian>() {
        public PeiLian createFromParcel(Parcel source) {
            return new PeiLian(source);
        }

        public PeiLian[] newArray(int size) {
            return new PeiLian[size];
        }
    };
}