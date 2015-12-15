package com.qingsongjia.qingsongjia.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanggang on 15/12/12.
 */
public class StreetView implements Parcelable {

    /**
     * did : 131
     * dri_campus_id : 291
     * dri_campus_nm : 英明驾校
     * dri_file_nm : 12
     * dri_file_path : http://7xlt5l.com1.z0.glb.clouddn.com/1449886785833deqsdy2899325
     * dri_fx_campus_nm :
     * id : 131
     */

    private int did;
    private int dri_campus_id;
    private String dri_campus_nm;
    private String dri_file_nm;
    private String dri_file_path;
    private String dri_fx_campus_nm;
    private int id;

    public void setDid(int did) {
        this.did = did;
    }

    public void setDri_campus_id(int dri_campus_id) {
        this.dri_campus_id = dri_campus_id;
    }

    public void setDri_campus_nm(String dri_campus_nm) {
        this.dri_campus_nm = dri_campus_nm;
    }

    public void setDri_file_nm(String dri_file_nm) {
        this.dri_file_nm = dri_file_nm;
    }

    public void setDri_file_path(String dri_file_path) {
        this.dri_file_path = dri_file_path;
    }

    public void setDri_fx_campus_nm(String dri_fx_campus_nm) {
        this.dri_fx_campus_nm = dri_fx_campus_nm;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDri_file_nm() {
        return dri_file_nm;
    }

    public String getDri_file_path() {
        return dri_file_path;
    }

    public String getDri_fx_campus_nm() {
        return dri_fx_campus_nm;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.did);
        dest.writeInt(this.dri_campus_id);
        dest.writeString(this.dri_campus_nm);
        dest.writeString(this.dri_file_nm);
        dest.writeString(this.dri_file_path);
        dest.writeString(this.dri_fx_campus_nm);
        dest.writeInt(this.id);
    }

    public StreetView() {
    }

    protected StreetView(Parcel in) {
        this.did = in.readInt();
        this.dri_campus_id = in.readInt();
        this.dri_campus_nm = in.readString();
        this.dri_file_nm = in.readString();
        this.dri_file_path = in.readString();
        this.dri_fx_campus_nm = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<StreetView> CREATOR = new Parcelable.Creator<StreetView>() {
        public StreetView createFromParcel(Parcel source) {
            return new StreetView(source);
        }

        public StreetView[] newArray(int size) {
            return new StreetView[size];
        }
    };
}
