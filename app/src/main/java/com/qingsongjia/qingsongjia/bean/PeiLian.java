package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/6.
 */
public class PeiLian {

    /**
     * carType :
     * contactName :
     * create_id : 0
     * create_tm : {"date":6,"day":0,"hours":12,"minutes":38,"month":11,"nanos":0,"seconds":47,"time":1449376727000,"timezoneOffset":-480,"year":115}
     * did : 112
     * dri_coach_id : 0
     * dri_coach_nm :
     * dri_comments :
     * dri_grab_date : null
     * dri_grab_date_str :
     * dri_partner_type :
     * dri_partner_type_nm :
     * dri_price : 0
     * dri_remark :
     * dri_remark_state :
     * dri_remark_state_nm :
     * dri_user_id : 0
     * id : 112
     * isdel : 0
     * meetingDate : {"date":6,"day":5,"hours":0,"minutes":0,"month":10,"seconds":0,"time":1446739200000,"timezoneOffset":-480,"year":115}
     * meetingDate_str : 2015-11-06
     * meetingTime : 0
     * status : 1
     * telephoneNumber :
     * update_id : 0
     * update_tm : {"date":6,"day":0,"hours":12,"minutes":38,"month":11,"nanos":0,"seconds":47,"time":1449376727000,"timezoneOffset":-480,"year":115}
     * validateCode :
     */

    private String carType;
    private String contactName;
    private int create_id;
    /**
     * date : 6
     * day : 0
     * hours : 12
     * minutes : 38
     * month : 11
     * nanos : 0
     * seconds : 47
     * time : 1449376727000
     * timezoneOffset : -480
     * year : 115
     */

    private CreateTmEntity create_tm;
    private int did;
    private int dri_coach_id;
    private String dri_coach_nm;
    private String dri_comments;
    private Object dri_grab_date;
    private String dri_grab_date_str;
    private String dri_partner_type;
    private String dri_partner_type_nm;
    private int dri_price;
    private String dri_remark;
    private String dri_remark_state;
    private String dri_remark_state_nm;
    private int dri_user_id;
    private int id;
    private String isdel;
    /**
     * date : 6
     * day : 5
     * hours : 0
     * minutes : 0
     * month : 10
     * seconds : 0
     * time : 1446739200000
     * timezoneOffset : -480
     * year : 115
     */

    private MeetingDateEntity meetingDate;
    private String meetingDate_str;
    private int meetingTime;
    private String status;
    private String telephoneNumber;
    private int update_id;
    /**
     * date : 6
     * day : 0
     * hours : 12
     * minutes : 38
     * month : 11
     * nanos : 0
     * seconds : 47
     * time : 1449376727000
     * timezoneOffset : -480
     * year : 115
     */

    private UpdateTmEntity update_tm;
    private String validateCode;

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public void setCreate_tm(CreateTmEntity create_tm) {
        this.create_tm = create_tm;
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

    public void setDri_grab_date(Object dri_grab_date) {
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

    public void setDri_remark_state(String dri_remark_state) {
        this.dri_remark_state = dri_remark_state;
    }

    public void setDri_remark_state_nm(String dri_remark_state_nm) {
        this.dri_remark_state_nm = dri_remark_state_nm;
    }

    public void setDri_user_id(int dri_user_id) {
        this.dri_user_id = dri_user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public void setMeetingDate(MeetingDateEntity meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingDate_str(String meetingDate_str) {
        this.meetingDate_str = meetingDate_str;
    }

    public void setMeetingTime(int meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public void setUpdate_tm(UpdateTmEntity update_tm) {
        this.update_tm = update_tm;
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

    public CreateTmEntity getCreate_tm() {
        return create_tm;
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

    public String getDri_remark_state() {
        return dri_remark_state;
    }

    public String getDri_remark_state_nm() {
        return dri_remark_state_nm;
    }

    public int getDri_user_id() {
        return dri_user_id;
    }

    public int getId() {
        return id;
    }

    public String getIsdel() {
        return isdel;
    }

    public MeetingDateEntity getMeetingDate() {
        return meetingDate;
    }

    public String getMeetingDate_str() {
        return meetingDate_str;
    }

    public int getMeetingTime() {
        return meetingTime;
    }

    public String getStatus() {
        return status;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getUpdate_id() {
        return update_id;
    }

    public UpdateTmEntity getUpdate_tm() {
        return update_tm;
    }

    public String getValidateCode() {
        return validateCode;
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

    public static class MeetingDateEntity {
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
