package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/5.
 */
public class UserData {

//    private Integer id;//主键
//    private Integer dri_campus_id;//驾校编码
//    private String dri_campus_nm;//驾校名称
//    private Integer dri_fx_campus_id;//分校编码
//    private String dri_fx_campus_nm;//分校编码
//    private String dri_nm;//姓名
//    private String dri_consult_state;//客户状态
//    private String dri_tel;//电话
//    private String dri_goal;//目的
//    private String dri_car_type;//车型
//    private String dri_id_no;//身份证号
//    private Date dri_review_dt;//回访时间
//    private Date dri_next_review_dt;//下次回访时间
//    private Date dri_course_dt;//上课时间
//    private String dri_course_pro;//上课进度
//    private Integer dri_coach_id;//教练编码
//    private String dri_coach_nm;//教练名称
//    private Double dri_tuition_fee;//学费总值
//    private String dri_remark;//备注
//    private String dri_consult_flag="1";//信息客户查询显示，flag=1显示，flag=0 不显示
//    private String dri_pq_nm;//所在片区
//    private final String DIC_DRI_CONSULT_STATE = "13";//（/客户状态） -- 字典
//    private final String DIC_DRI_GOAL = "14";//（目的） -- 字典
//    private final String DIC_DRI_CAR_TYPE = "15";//（车型） -- 字典
//    private final String DIC_DRI_COURSE_PRO = "16";//（上课进度） -- 字典
//
//    private String dri_pass_word;//密码
//    private String dri_invitation_code;//邀请码


    private int campusId;
    private String campusName;
    private int create_id;
    private String create_nm;

    private CreateTmEntity create_tm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private String dri_car_type;
    private String dri_car_type_nm;
    private int dri_coach_id;
    private String dri_coach_nm;
    private String dri_consult_flag;
    private String dri_consult_state;
    private String dri_consult_state_nm;
    private Object dri_course_dt;
    private String dri_course_dt_str;
    private String dri_course_pro;
    private String dri_course_pro_nm;
    private String dri_file_path;
    private int dri_fx_campus_id;
    private String dri_fx_campus_nm;
    private String dri_goal;
    private String dri_goal_nm;
    private String dri_id_no;
    private String dri_invitation_code;
    private Object dri_next_review_dt;
    private String dri_next_review_dt_str;
    private String dri_nm;
    private String dri_pass_word;
    private String dri_pq_nm;
    private String dri_remark;
    private Object dri_review_dt;
    private String dri_review_dt_str;
    private String dri_tel;
    private int dri_tuition_fee;
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

    public void setDri_car_type(String dri_car_type) {
        this.dri_car_type = dri_car_type;
    }

    public void setDri_car_type_nm(String dri_car_type_nm) {
        this.dri_car_type_nm = dri_car_type_nm;
    }

    public void setDri_coach_id(int dri_coach_id) {
        this.dri_coach_id = dri_coach_id;
    }

    public void setDri_coach_nm(String dri_coach_nm) {
        this.dri_coach_nm = dri_coach_nm;
    }

    public void setDri_consult_flag(String dri_consult_flag) {
        this.dri_consult_flag = dri_consult_flag;
    }

    public void setDri_consult_state(String dri_consult_state) {
        this.dri_consult_state = dri_consult_state;
    }

    public void setDri_consult_state_nm(String dri_consult_state_nm) {
        this.dri_consult_state_nm = dri_consult_state_nm;
    }

    public void setDri_course_dt(Object dri_course_dt) {
        this.dri_course_dt = dri_course_dt;
    }

    public void setDri_course_dt_str(String dri_course_dt_str) {
        this.dri_course_dt_str = dri_course_dt_str;
    }

    public void setDri_course_pro(String dri_course_pro) {
        this.dri_course_pro = dri_course_pro;
    }

    public void setDri_course_pro_nm(String dri_course_pro_nm) {
        this.dri_course_pro_nm = dri_course_pro_nm;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public void setDri_fx_campus_id(int dri_fx_campus_id) {
        this.dri_fx_campus_id = dri_fx_campus_id;
    }

    public void setDri_fx_campus_nm(String dri_fx_campus_nm) {
        this.dri_fx_campus_nm = dri_fx_campus_nm;
    }

    public void setDri_goal(String dri_goal) {
        this.dri_goal = dri_goal;
    }

    public void setDri_goal_nm(String dri_goal_nm) {
        this.dri_goal_nm = dri_goal_nm;
    }

    public void setDri_id_no(String dri_id_no) {
        this.dri_id_no = dri_id_no;
    }

    public void setDri_invitation_code(String dri_invitation_code) {
        this.dri_invitation_code = dri_invitation_code;
    }

    public void setDri_next_review_dt(Object dri_next_review_dt) {
        this.dri_next_review_dt = dri_next_review_dt;
    }

    public void setDri_next_review_dt_str(String dri_next_review_dt_str) {
        this.dri_next_review_dt_str = dri_next_review_dt_str;
    }

    public void setDri_nm(String dri_nm) {
        this.dri_nm = dri_nm;
    }

    public void setDri_pass_word(String dri_pass_word) {
        this.dri_pass_word = dri_pass_word;
    }

    public void setDri_pq_nm(String dri_pq_nm) {
        this.dri_pq_nm = dri_pq_nm;
    }

    public void setDri_remark(String dri_remark) {
        this.dri_remark = dri_remark;
    }

    public void setDri_review_dt(Object dri_review_dt) {
        this.dri_review_dt = dri_review_dt;
    }

    public void setDri_review_dt_str(String dri_review_dt_str) {
        this.dri_review_dt_str = dri_review_dt_str;
    }

    public void setDri_tel(String dri_tel) {
        this.dri_tel = dri_tel;
    }

    public void setDri_tuition_fee(int dri_tuition_fee) {
        this.dri_tuition_fee = dri_tuition_fee;
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

    public String getDri_car_type() {
        return dri_car_type;
    }

    public String getDri_car_type_nm() {
        return dri_car_type_nm;
    }

    public int getDri_coach_id() {
        return dri_coach_id;
    }

    public String getDri_coach_nm() {
        return dri_coach_nm;
    }

    public String getDri_consult_flag() {
        return dri_consult_flag;
    }

    public String getDri_consult_state() {
        return dri_consult_state;
    }

    public String getDri_consult_state_nm() {
        return dri_consult_state_nm;
    }

    public Object getDri_course_dt() {
        return dri_course_dt;
    }

    public String getDri_course_dt_str() {
        return dri_course_dt_str;
    }

    public String getDri_course_pro() {
        return dri_course_pro;
    }

    public String getDri_course_pro_nm() {
        return dri_course_pro_nm;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }

    public int getDri_fx_campus_id() {
        return dri_fx_campus_id;
    }

    public String getDri_fx_campus_nm() {
        return dri_fx_campus_nm;
    }

    public String getDri_goal() {
        return dri_goal;
    }

    public String getDri_goal_nm() {
        return dri_goal_nm;
    }

    public String getDri_id_no() {
        return dri_id_no;
    }

    public String getDri_invitation_code() {
        return dri_invitation_code;
    }

    public Object getDri_next_review_dt() {
        return dri_next_review_dt;
    }

    public String getDri_next_review_dt_str() {
        return dri_next_review_dt_str;
    }

    public String getDri_nm() {
        return dri_nm;
    }

    public String getDri_pass_word() {
        return dri_pass_word;
    }

    public String getDri_pq_nm() {
        return dri_pq_nm;
    }

    public String getDri_remark() {
        return dri_remark;
    }

    public Object getDri_review_dt() {
        return dri_review_dt;
    }

    public String getDri_review_dt_str() {
        return dri_review_dt_str;
    }

    public String getDri_tel() {
        return dri_tel;
    }

    public int getDri_tuition_fee() {
        return dri_tuition_fee;
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
