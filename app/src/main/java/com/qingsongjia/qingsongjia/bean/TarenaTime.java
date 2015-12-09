package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanggang on 15/12/9.
 */
public class TarenaTime implements Parcelable {

    /**
     * date : 27
     * day : 5
     * hours : 0
     * minutes : 0
     * month : 10
     * seconds : 0
     * time : 1448553600000
     * timezoneOffset : -480
     * year : 115
     */

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.date);
        dest.writeInt(this.day);
        dest.writeInt(this.hours);
        dest.writeInt(this.minutes);
        dest.writeInt(this.month);
        dest.writeInt(this.seconds);
        dest.writeLong(this.time);
        dest.writeInt(this.timezoneOffset);
        dest.writeInt(this.year);
    }

    public TarenaTime() {
    }

    protected TarenaTime(Parcel in) {
        this.date = in.readInt();
        this.day = in.readInt();
        this.hours = in.readInt();
        this.minutes = in.readInt();
        this.month = in.readInt();
        this.seconds = in.readInt();
        this.time = in.readLong();
        this.timezoneOffset = in.readInt();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<TarenaTime> CREATOR = new Parcelable.Creator<TarenaTime>() {
        public TarenaTime createFromParcel(Parcel source) {
            return new TarenaTime(source);
        }

        public TarenaTime[] newArray(int size) {
            return new TarenaTime[size];
        }
    };
}
