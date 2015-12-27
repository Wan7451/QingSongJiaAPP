package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by wanggang on 15/12/11.
 */
public class SchoolDetail implements Parcelable {



    //    "countpepole": 0,
//            "dri_address": "山西省太原市东岗南路252-30号（南内环街现代女子医院南300米）",
//            "dri_campus_id": 288,
//            "dri_file_path": "http://7xlt5l.com1.z0.glb.clouddn.com/1449805758007orzzax9658691",
//            "dri_map_address": "",
//            "dri_money": 3600,
//            "dri_nm": "东风驾校",
//            "dri_pass": 0,
//            "dri_place": 0,
//            "dri_report": "欢迎光临太原东风驾校，东风驾校电话：0351-7683105， 太原东风驾校怎么样，价格贵不贵？东风驾校好不好？ 东风驾校地址：山西省太原市东岗南路252-30号（南内环街现代女子医院南300米）\r\n业务咨询电话 ：0351-7683105，7683195 \r\n1、真正的正规驾校：有自主考场，考试快通过率高；\r\n20-30 年驾龄老师傅，与你分享全路况驾校经验；\r\n2、20 年驾校办学经验； \r\n3、30台注册皮卡，捷达教练车，车况良好、不论刮风下雪，练车时间充足保障； \r\n4、8 人以上团体报名，另有惊喜赠送； \r\n5、1 站式服务，涵盖：学车、落户、体检、审本、审车、违章处理、代驾等； \r\n6、95%年平均通过率，随到随学，循环培训，循环考核，； \r\n7、每期超百人的学员容量，固定紧密的社交平台；",
//            "dri_sum": 0,
//            "dri_tel1": "0351-7683195",
//            "dri_tel2": "0351-7683105",
//            "dri_time": 0,
//            "dri_way": "",
//            "street_view": []


    private int countpepole;
    private String dri_address;
    private int dri_campus_id;
    private String dri_file_path;
    private String dri_map_address;
    private int dri_money;
    private String dri_nm;
    private int dri_pass;
    private int dri_place;
    private int dri_sum;
    private String dri_report;

    private String dri_tel1;
    private String dri_tel2;
    private String dri_way;
    private int dri_time;
    private ArrayList<StreetView> street_view;
    /**
     * registFees : 200
     */

    private int registFees;

    public SchoolDetail() {
    }

    public int getCountpepole() {
        return countpepole;
    }

    public void setCountpepole(int countpepole) {
        this.countpepole = countpepole;
    }

    public String getDri_address() {
        return dri_address;
    }

    public void setDri_address(String dri_address) {
        this.dri_address = dri_address;
    }

    public int getDri_campus_id() {
        return dri_campus_id;
    }

    public void setDri_campus_id(int dri_campus_id) {
        this.dri_campus_id = dri_campus_id;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public String getDri_map_address() {
        return dri_map_address;
    }

    public void setDri_map_address(String dri_map_address) {
        this.dri_map_address = dri_map_address;
    }

    public int getDri_money() {
        return dri_money;
    }

    public void setDri_money(int dri_money) {
        this.dri_money = dri_money;
    }

    public String getDri_nm() {
        return dri_nm;
    }

    public void setDri_nm(String dri_nm) {
        this.dri_nm = dri_nm;
    }

    public int getDri_pass() {
        return dri_pass;
    }

    public void setDri_pass(int dri_pass) {
        this.dri_pass = dri_pass;
    }

    public int getDri_place() {
        return dri_place;
    }

    public void setDri_place(int dri_place) {
        this.dri_place = dri_place;
    }

    public int getDri_sum() {
        return dri_sum;
    }

    public void setDri_sum(int dri_sum) {
        this.dri_sum = dri_sum;
    }

    public String getDri_report() {
        return dri_report;
    }

    public void setDri_report(String dri_report) {
        this.dri_report = dri_report;
    }

    public String getDri_tel1() {
        return dri_tel1;
    }

    public void setDri_tel1(String dri_tel1) {
        this.dri_tel1 = dri_tel1;
    }

    public String getDri_tel2() {
        return dri_tel2;
    }

    public void setDri_tel2(String dri_tel2) {
        this.dri_tel2 = dri_tel2;
    }

    public String getDri_way() {
        return dri_way;
    }

    public void setDri_way(String dri_way) {
        this.dri_way = dri_way;
    }

    public int getDri_time() {
        return dri_time;
    }

    public void setDri_time(int dri_time) {
        this.dri_time = dri_time;
    }

    public ArrayList<StreetView> getStreet_view() {
        return street_view;
    }

    public void setStreet_view(ArrayList<StreetView> street_view) {
        this.street_view = street_view;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.countpepole);
        dest.writeString(this.dri_address);
        dest.writeInt(this.dri_campus_id);
        dest.writeString(this.dri_file_path);
        dest.writeString(this.dri_map_address);
        dest.writeInt(this.dri_money);
        dest.writeString(this.dri_nm);
        dest.writeInt(this.dri_pass);
        dest.writeInt(this.dri_place);
        dest.writeInt(this.dri_sum);
        dest.writeString(this.dri_report);
        dest.writeString(this.dri_tel1);
        dest.writeString(this.dri_tel2);
        dest.writeString(this.dri_way);
        dest.writeInt(this.dri_time);
        dest.writeList(this.street_view);
    }

    protected SchoolDetail(Parcel in) {
        this.countpepole = in.readInt();
        this.dri_address = in.readString();
        this.dri_campus_id = in.readInt();
        this.dri_file_path = in.readString();
        this.dri_map_address = in.readString();
        this.dri_money = in.readInt();
        this.dri_nm = in.readString();
        this.dri_pass = in.readInt();
        this.dri_place = in.readInt();
        this.dri_sum = in.readInt();
        this.dri_report = in.readString();
        this.dri_tel1 = in.readString();
        this.dri_tel2 = in.readString();
        this.dri_way = in.readString();
        this.dri_time = in.readInt();
        this.street_view = new ArrayList<>();
        in.readList(this.street_view, getClass().getClassLoader());
    }

    public static final Creator<SchoolDetail> CREATOR = new Creator<SchoolDetail>() {
        public SchoolDetail createFromParcel(Parcel source) {
            return new SchoolDetail(source);
        }

        public SchoolDetail[] newArray(int size) {
            return new SchoolDetail[size];
        }
    };

    public void setRegistFees(int registFees) {
        this.registFees = registFees;
    }

    public int getRegistFees() {
        return registFees;
    }
}
