package com.qingsongjia.qingsongjia.bean;

import java.util.Date;

/**
 * Created by wanggang on 15/12/2.
 */
public class Teacher {

//    private Integer id;//（账户编码）
//    private Integer dri_fx_campus_id;//（分校编码）
//    private String dri_sub_nm;//（科目名称）
//    private Integer dri_coach_id;//(教练名称)
//    private String dri_coach_nm;//(教练名称)
//    private Date dri_date;//(日期)
//    private Integer dri_start_hm;//开始时刻
//    private Integer dri_campus_id;//（主校）	integer		（为空则代表：主校区，不为空：看做分校区）
//    private Integer dri_end_hm;//（截止时刻）
//    private Integer dri_rv_num;//（最大预约人数）
//    private Integer dri_rvd_num;//已预约人数
//    private String dri_report;//（练习内容）
//    private String dri_campus_nm;	//主校名称
//    private String dri_fx_campus_nm;//分校名称
//    private String dri_plate_num;//车牌号
//    private final String DIC_DRI_SUB_NM = "1";//（是否可用） -- 字典


    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;
    private CreateTmEntity create_tm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    private DriDateEntity dri_date;
    private String dri_date_str;
    private int dri_end_hm;
    private int dri_fx_campus_id;
    private String dri_fx_campus_nm;
    private String dri_plate_num;
    private String dri_report;
    private int dri_rv_num;
    private int dri_rvd_num;
    private int dri_start_hm;
    private String dri_sub_nm;
    private String dri_sub_nm_nm;
    private int id;
    private int isdel;
    private String name;
    private int update_id;
    private String update_nm;
    private UpdateTmEntity update_tm;
    private String update_tm_str;

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

    public void setCreate_tm(CreateTmEntity create_tm) {
        this.create_tm = create_tm;
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

    public void setDri_date(DriDateEntity dri_date) {
        this.dri_date = dri_date;
    }

    public void setDri_date_str(String dri_date_str) {
        this.dri_date_str = dri_date_str;
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

    public void setDri_plate_num(String dri_plate_num) {
        this.dri_plate_num = dri_plate_num;
    }

    public void setDri_report(String dri_report) {
        this.dri_report = dri_report;
    }

    public void setDri_rv_num(int dri_rv_num) {
        this.dri_rv_num = dri_rv_num;
    }

    public void setDri_rvd_num(int dri_rvd_num) {
        this.dri_rvd_num = dri_rvd_num;
    }

    public void setDri_start_hm(int dri_start_hm) {
        this.dri_start_hm = dri_start_hm;
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

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public void setUpdate_nm(String update_nm) {
        this.update_nm = update_nm;
    }

    public void setUpdate_tm(UpdateTmEntity update_tm) {
        this.update_tm = update_tm;
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

    public CreateTmEntity getCreate_tm() {
        return create_tm;
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

    public DriDateEntity getDri_date() {
        return dri_date;
    }

    public String getDri_date_str() {
        return dri_date_str;
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

    public String getDri_plate_num() {
        return dri_plate_num;
    }

    public String getDri_report() {
        return dri_report;
    }

    public int getDri_rv_num() {
        return dri_rv_num;
    }

    public int getDri_rvd_num() {
        return dri_rvd_num;
    }

    public int getDri_start_hm() {
        return dri_start_hm;
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

    public int getUpdate_id() {
        return update_id;
    }

    public String getUpdate_nm() {
        return update_nm;
    }

    public UpdateTmEntity getUpdate_tm() {
        return update_tm;
    }

    public String getUpdate_tm_str() {
        return update_tm_str;
    }

    public static class CreateTmEntity {
        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public void setDate(int date) {
            this.date = date;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDate() {
            return date;
        }

        public int getDay() {
            return day;
        }

        public int getHours() {
            return hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public int getMonth() {
            return month;
        }

        public int getNanos() {
            return nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public long getTime() {
            return time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public int getYear() {
            return year;
        }
    }

    public static class DriDateEntity {
        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;


        public void setDate(int date) {
            this.date = date;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDate() {
            return date;
        }

        public int getDay() {
            return day;
        }

        public int getHours() {
            return hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public int getMonth() {
            return month;
        }

        public int getSeconds() {
            return seconds;
        }

        public long getTime() {
            return time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public int getYear() {
            return year;
        }
    }

    public static class UpdateTmEntity {
        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public void setDate(int date) {
            this.date = date;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDate() {
            return date;
        }

        public int getDay() {
            return day;
        }

        public int getHours() {
            return hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public int getMonth() {
            return month;
        }

        public int getNanos() {
            return nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public long getTime() {
            return time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public int getYear() {
            return year;
        }
    }
}
