package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanggang on 15/12/11.
 */
public class CityData implements Parcelable {
    private String code;
    private String parentCodeName;
    private String name;
    private String codeName;

    public CityData() {
    }

    public CityData(String code, String codeName, String name, String parentCodeName) {
        this.code = code;
        this.parentCodeName = parentCodeName;
        this.name = name;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCodeName() {
        return parentCodeName;
    }

    public void setParentCodeName(String parentCodeName) {
        this.parentCodeName = parentCodeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.parentCodeName);
        dest.writeString(this.name);
        dest.writeString(this.codeName);
    }

    protected CityData(Parcel in) {
        this.code = in.readString();
        this.parentCodeName = in.readString();
        this.name = in.readString();
        this.codeName = in.readString();
    }

    public static final Parcelable.Creator<CityData> CREATOR = new Parcelable.Creator<CityData>() {
        public CityData createFromParcel(Parcel source) {
            return new CityData(source);
        }

        public CityData[] newArray(int size) {
            return new CityData[size];
        }
    };
}
