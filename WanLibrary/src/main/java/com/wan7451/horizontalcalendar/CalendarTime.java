package com.wan7451.horizontalcalendar;

/**
 * Created by Wan7451 on 2015/7/9.
 */
public class CalendarTime {

    private int week;
    private int day;
    private int month;
    private int year;
    private boolean isHasData;
    private boolean isShowIndicator;
    private String time;

    @Override
    public String toString() {
        return "CalendarTime{" +
                "week=" + week +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", isHasData=" + isHasData +
                ", isShowIndicator=" + isShowIndicator +
                ", time='" + time + '\'' +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isHasData() {
        return isHasData;
    }

    public void setIsHasData(boolean isHasData) {
        this.isHasData = isHasData;
    }

    public boolean isShowIndicator() {
        return isShowIndicator;
    }

    public void setIsShowIndicator(boolean isShowIndicator) {
        this.isShowIndicator = isShowIndicator;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
