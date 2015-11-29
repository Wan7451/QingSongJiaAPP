package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args) {

        Schema schema = new Schema(1, "greendao");


        Entity note = schema.addEntity("Chapter");
        note.addIntProperty("id");
        note.addIntProperty("mid");
        note.addStringProperty("Str");
        note.addIntProperty("Fid");
        note.addIntProperty("kemu");
        note.addIntProperty("counts");

        Entity note2 = schema.addEntity("exam_detail");
        note2.addIntProperty("id");
        note2.addIntProperty("exam_id");
        note2.addIntProperty("test_id");
        note2.addStringProperty("my_answer");
        note2.addIntProperty("car_type");
        note2.addIntProperty("kemu_type");
        note2.addIntProperty("is_wrong");


        Entity note3 = schema.addEntity("exam_result");
        note3.addIntProperty("id");
        note3.addIntProperty("score");
        note3.addLongProperty("use_time");
        note3.addStringProperty("result_name");
        note3.addStringProperty("user_name");
        note3.addStringProperty("user_face");
        note3.addStringProperty("kemu_type");
        note3.addStringProperty("car_type");
        note3.addDateProperty("add_time");
        note3.addIntProperty("question_count");
        note3.addIntProperty("right_count");
        note3.addStringProperty("user_id");


        Entity note4 = schema.addEntity("test_collect");
        note4.addIntProperty("test_id");
        note4.addDateProperty("add_time");
        note4.addIntProperty("car_type");
        note4.addIntProperty("kemu_type");


        Entity note5 = schema.addEntity("test_do");
        note5.addIntProperty("test_id");
        note5.addBooleanProperty("is_show_in_wrong");
        note5.addStringProperty("my_answer");
        note5.addStringProperty("true_answer");
        note5.addBooleanProperty("is_correct");
        note5.addIntProperty("wrong_count");
        note5.addDateProperty("add_time");
        note5.addDateProperty("update_time");
        note5.addIntProperty("car_type");
        note5.addIntProperty("kemu_type");


        Entity note6 = schema.addEntity("test_remove");
        note6.addIntProperty("test_id");
        note6.addDateProperty("add_time");
        note6.addIntProperty("car_type");
        note6.addIntProperty("kemu_type");


        Entity note7 = schema.addEntity("web_note");
        note7.addIntProperty("ID").notNull();
        note7.addIntProperty("Type");
        note7.addStringProperty("intNumber");
        note7.addStringProperty("strTppe");
        note7.addStringProperty("strType_l");
        note7.addStringProperty("LicenseType");
        note7.addStringProperty("Question");
        note7.addStringProperty("An1");
        note7.addStringProperty("An2");
        note7.addStringProperty("An3");
        note7.addStringProperty("An4");
        note7.addStringProperty("An5");
        note7.addStringProperty("An6");
        note7.addStringProperty("An7");
        note7.addStringProperty("AnswerTrue");
        note7.addStringProperty("explain");
        note7.addIntProperty("BestAnswerId");
        note7.addIntProperty("kemu");
        note7.addStringProperty("jieshi_from");
        note7.addStringProperty("moretypes");
        note7.addStringProperty("sinaimg");
        note7.addStringProperty("video_url");
        note7.addIntProperty("diff_degree");
        note7.addIntProperty("chapterid");


        try {
            new DaoGenerator().
                    generateAll(schema,
                            "/Users/wanggang/Documents/MyApplicationQingSongJia/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
