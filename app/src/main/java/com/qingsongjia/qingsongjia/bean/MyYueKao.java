package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/5.
 */
public class MyYueKao {


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


    /**
     * DIC_DRI_STATE : 3
     * campusId : 0
     * campusName :
     * create_id : 106
     * create_nm : 张静雯
     * create_tm : {"date":29,"day":4,"hours":17,"minutes":35,"month":9,"nanos":0,"seconds":41,"time":1446111341000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-10-29 17:35:41
     * did : 137
     * dri_campus_id : 248
     * dri_campus_nm : 大正驾校
     * dri_coach_id : 106
     * dri_coach_nm : 张静雯
     * dri_confirm_tm : {"date":29,"day":4,"hours":17,"minutes":36,"month":9,"nanos":0,"seconds":10,"time":1446111370000,"timezoneOffset":-480,"year":115}
     * dri_confirm_tm_str : 2015-10-29 17:36:10
     * dri_dt : {"date":29,"day":4,"hours":0,"minutes":0,"month":9,"seconds":0,"time":1446048000000,"timezoneOffset":-480,"year":115}
     * dri_dt_str : 2015-10-29
     * dri_end_hm : 22
     * dri_fx_campus_id : 250
     * dri_fx_campus_nm : 大正分校
     * dri_plan : 194
     * dri_remarks :
     * dri_start_hm : 10
     * dri_state : 3
     * dri_state_nm : 已预约
     * dri_student_id : 112
     * dri_student_nm : 李杰飞
     * dri_sub_nm : 科目二
     * dri_sub_nm_nm :
     * id : 137
     * isdel : 0
     * name :
     * type :
     * update_id : 106
     * update_nm : 张静雯
     * update_tm : {"date":29,"day":4,"hours":17,"minutes":35,"month":9,"nanos":0,"seconds":41,"time":1446111341000,"timezoneOffset":-480,"year":115}
     * update_tm_str : 2015-10-29 17:35:41
     */

    private String DIC_DRI_STATE;
    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;
    /**
     * date : 29
     * day : 4
     * hours : 17
     * minutes : 35
     * month : 9
     * nanos : 0
     * seconds : 41
     * time : 1446111341000
     * timezoneOffset : -480
     * year : 115
     */

    private CreateTmEntity create_tm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    /**
     * date : 29
     * day : 4
     * hours : 17
     * minutes : 36
     * month : 9
     * nanos : 0
     * seconds : 10
     * time : 1446111370000
     * timezoneOffset : -480
     * year : 115
     */

    private DriConfirmTmEntity dri_confirm_tm;
    private String dri_confirm_tm_str;
    /**
     * date : 29
     * day : 4
     * hours : 0
     * minutes : 0
     * month : 9
     * seconds : 0
     * time : 1446048000000
     * timezoneOffset : -480
     * year : 115
     */

    private DriDtEntity dri_dt;
    private String dri_dt_str;
    private int dri_end_hm;
    private int dri_fx_campus_id;
    private String dri_fx_campus_nm;
    private int dri_plan;
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
    /**
     * date : 29
     * day : 4
     * hours : 17
     * minutes : 35
     * month : 9
     * nanos : 0
     * seconds : 41
     * time : 1446111341000
     * timezoneOffset : -480
     * year : 115
     */

    private UpdateTmEntity update_tm;
    private String update_tm_str;

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

    public void setDri_confirm_tm(DriConfirmTmEntity dri_confirm_tm) {
        this.dri_confirm_tm = dri_confirm_tm;
    }

    public void setDri_confirm_tm_str(String dri_confirm_tm_str) {
        this.dri_confirm_tm_str = dri_confirm_tm_str;
    }

    public void setDri_dt(DriDtEntity dri_dt) {
        this.dri_dt = dri_dt;
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

    public void setDri_plan(int dri_plan) {
        this.dri_plan = dri_plan;
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

    public void setUpdate_tm(UpdateTmEntity update_tm) {
        this.update_tm = update_tm;
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

    public DriConfirmTmEntity getDri_confirm_tm() {
        return dri_confirm_tm;
    }

    public String getDri_confirm_tm_str() {
        return dri_confirm_tm_str;
    }

    public DriDtEntity getDri_dt() {
        return dri_dt;
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

    public int getDri_plan() {
        return dri_plan;
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

    public static class DriConfirmTmEntity {
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

    public static class DriDtEntity {
        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        @Override
        public String toString() {
            return month+1+"月"+day+"日";
        }

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
