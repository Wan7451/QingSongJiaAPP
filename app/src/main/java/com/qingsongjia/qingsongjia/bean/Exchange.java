package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/12/27.
 */
public class Exchange {

//    private Integer id;  //文章ID
//    private String dri_title;// 内容标题
//    private String dri_text;// 内容
//    private String dri_image_url;// 文章上传图片所对应的企牛存储地址用逗号区分多个图片
//    private int praise;// 文章所对应的赞的个数
//    private int hate;// 文章所对应的踩的个数
//    private String user_file;//创建人头像地址
//    private String create_nm;//（创建人名称）
//    private int create_id;//（创建人ID）
//    private Timestamp create_tm;//（创建时间）
//    private String create_tm_str;//（创建时间-文本）
//    private int comment_count;//帖子评论数




    private int create_id;
    private String create_nm;
    private String create_tm_str;
    private int did;
    private int dri_campus_id;
    private String dri_eaa_state;
    private String dri_eaa_state_nm;
    private int dri_fx_campus_id;
    private String dri_image_url;
    private String dri_num;
    private String dri_reply_id;
    private String dri_reply_type;
    private String dri_text;
    private String dri_title;
    private String dri_type;
    private int id;
    private int isdel;
    private String name;
    private int update_id;
    private String update_nm;
    private String update_tm_str;
    /**
     * campusId : 0
     * campusName :
     * comment_count : 0
     * create_tm : {"date":17,"day":4,"hours":14,"minutes":11,"month":11,"nanos":0,"seconds":11,"time":1450332671000,"timezoneOffset":-480,"year":115}
     * hate : 0
     * praise : 0
     * update_tm : {"date":23,"day":3,"hours":15,"minutes":49,"month":11,"nanos":0,"seconds":2,"time":1450856942000,"timezoneOffset":-480,"year":115}
     * user_file :
     */

    private int campusId;
    private String campusName;
    private int comment_count;
    private int hate;
    private int praise;
    private String user_file;

    private int praise_if;//0 未点赞 >0已赞

    public int getPraise_if() {
        return praise_if;
    }

    public void setPraise_if(int praise_if) {
        this.praise_if = praise_if;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public void setCreate_nm(String create_nm) {
        this.create_nm = create_nm;
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

    public void setDri_eaa_state(String dri_eaa_state) {
        this.dri_eaa_state = dri_eaa_state;
    }

    public void setDri_eaa_state_nm(String dri_eaa_state_nm) {
        this.dri_eaa_state_nm = dri_eaa_state_nm;
    }

    public void setDri_fx_campus_id(int dri_fx_campus_id) {
        this.dri_fx_campus_id = dri_fx_campus_id;
    }

    public void setDri_image_url(String dri_image_url) {
        this.dri_image_url = dri_image_url;
    }

    public void setDri_num(String dri_num) {
        this.dri_num = dri_num;
    }

    public void setDri_reply_id(String dri_reply_id) {
        this.dri_reply_id = dri_reply_id;
    }

    public void setDri_reply_type(String dri_reply_type) {
        this.dri_reply_type = dri_reply_type;
    }

    public void setDri_text(String dri_text) {
        this.dri_text = dri_text;
    }

    public void setDri_title(String dri_title) {
        this.dri_title = dri_title;
    }

    public void setDri_type(String dri_type) {
        this.dri_type = dri_type;
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

    public void setUpdate_tm_str(String update_tm_str) {
        this.update_tm_str = update_tm_str;
    }

    public int getCreate_id() {
        return create_id;
    }

    public String getCreate_nm() {
        return create_nm;
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

    public String getDri_eaa_state() {
        return dri_eaa_state;
    }

    public String getDri_eaa_state_nm() {
        return dri_eaa_state_nm;
    }

    public int getDri_fx_campus_id() {
        return dri_fx_campus_id;
    }

    public String getDri_image_url() {
        return dri_image_url;
    }

    public String getDri_num() {
        return dri_num;
    }

    public String getDri_reply_id() {
        return dri_reply_id;
    }

    public String getDri_reply_type() {
        return dri_reply_type;
    }

    public String getDri_text() {
        return dri_text;
    }

    public String getDri_title() {
        return dri_title;
    }

    public String getDri_type() {
        return dri_type;
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

    public String getUpdate_tm_str() {
        return update_tm_str;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setHate(int hate) {
        this.hate = hate;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public void setUser_file(String user_file) {
        this.user_file = user_file;
    }

    public int getCampusId() {
        return campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getHate() {
        return hate;
    }

    public int getPraise() {
        return praise;
    }

    public String getUser_file() {
        return user_file;
    }
}
