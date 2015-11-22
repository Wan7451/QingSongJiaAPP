package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/22.
 * 测试题对象
 */
public class ExamSubject {

    private int id;
    private int type;//驾照类型
    private int question_type;//题目类型   0单选 1多选
    private String knowledge_point;//知识点
    private int sequence_num;//顺序号
    private String questions; //题目
    private String questions_picture;//题目图片
    private String option_a;//答案A
    private String option_b;//答案B
    private String option_c;//答案C
    private String option_d;//答案D
    private String option_a_picture;//答案A图片
    private String option_b_picture;//答案B图片
    private String option_c_picture;//答案C图片
    private String option_d_picture;//答案D图片
    private int answer; //答案内容
    private int mistake_times; //错误次数
    private int subject_type; //科目类型


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }

    public String getKnowledge_point() {
        return knowledge_point;
    }

    public void setKnowledge_point(String knowledge_point) {
        this.knowledge_point = knowledge_point;
    }

    public int getSequence_num() {
        return sequence_num;
    }

    public void setSequence_num(int sequence_num) {
        this.sequence_num = sequence_num;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getQuestions_picture() {
        return questions_picture;
    }

    public void setQuestions_picture(String questions_picture) {
        this.questions_picture = questions_picture;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getOption_a_picture() {
        return option_a_picture;
    }

    public void setOption_a_picture(String option_a_picture) {
        this.option_a_picture = option_a_picture;
    }

    public String getOption_b_picture() {
        return option_b_picture;
    }

    public void setOption_b_picture(String option_b_picture) {
        this.option_b_picture = option_b_picture;
    }

    public String getOption_c_picture() {
        return option_c_picture;
    }

    public void setOption_c_picture(String option_c_picture) {
        this.option_c_picture = option_c_picture;
    }

    public String getOption_d_picture() {
        return option_d_picture;
    }

    public void setOption_d_picture(String option_d_picture) {
        this.option_d_picture = option_d_picture;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getMistake_times() {
        return mistake_times;
    }

    public void setMistake_times(int mistake_times) {
        this.mistake_times = mistake_times;
    }

    public int getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(int subject_type) {
        this.subject_type = subject_type;
    }
}
