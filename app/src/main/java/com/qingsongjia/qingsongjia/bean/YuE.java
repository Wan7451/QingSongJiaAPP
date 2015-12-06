package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/6.
 */
public class YuE {


    /**
     * campusId : 0
     * campusName :
     * card_num : 62284899895187
     * create_id : 0
     * create_nm :
     * create_tm : {"date":20,"day":5,"hours":16,"minutes":6,"month":10,"nanos":0,"seconds":9,"time":1448006769000,"timezoneOffset":-480,"year":115}
     * create_tm_str : 2015-11-20 16:06:09
     * did : 4
     * id : 4
     * isdel : 0
     * name :
     * opr :
     * type : 充值
     * update_id : 0
     * update_nm :
     * update_tm : null
     * update_tm_str :
     * user_id : 112
     * worth : -70
     */

    private int campusId;
    private String campusName;
    private String card_num;
    private int create_id;
    private String create_nm;
    /**
     * date : 20
     * day : 5
     * hours : 16
     * minutes : 6
     * month : 10
     * nanos : 0
     * seconds : 9
     * time : 1448006769000
     * timezoneOffset : -480
     * year : 115
     */

    private CreateTmEntity create_tm;
    private String create_tm_str;
    private int did;
    private int id;
    private int isdel;
    private String name;
    private String opr;
    private String type;
    private int update_id;
    private String update_nm;
    private Object update_tm;
    private String update_tm_str;
    private int user_id;
    private int worth;

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpr(String opr) {
        this.opr = opr;
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

    public void setUpdate_tm(Object update_tm) {
        this.update_tm = update_tm;
    }

    public void setUpdate_tm_str(String update_tm_str) {
        this.update_tm_str = update_tm_str;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public int getCampusId() {
        return campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public String getCard_num() {
        return card_num;
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

    public int getId() {
        return id;
    }

    public int getIsdel() {
        return isdel;
    }

    public String getName() {
        return name;
    }

    public String getOpr() {
        return opr;
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

    public Object getUpdate_tm() {
        return update_tm;
    }

    public String getUpdate_tm_str() {
        return update_tm_str;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getWorth() {
        return worth;
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
}
