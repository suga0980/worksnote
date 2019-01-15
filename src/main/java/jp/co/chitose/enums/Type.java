package jp.co.chitose.enums;

import java.util.Arrays;
import java.util.List;

public enum Type {
    FD("FD活動", Arrays.asList("学内行事", "学外行事", "講師活動")),
    CLASS_ADVICER("クラスアドバイザー活動", Arrays.asList("")),
    STUDENT_SUPPORT("生徒支援活動", Arrays.asList("平日", "休日")),
    JOB_SUPPORT("就職支援活動", Arrays.asList("企業紹介・訪問", "就職イベント参加")),
    GET_STUDENT("学生確保活動", Arrays.asList("学外", "学内（平日）", "学内（休日）")),
    REGION_SUPPORT("地域支援活動", Arrays.asList("")),
    ;

    private final String text;
    private final List<String> list;

    private Type(final String text, final List<String> list) {
        this.text = text;
        this.list = list;
    }

    public String getText() {
        return text;
    }

    public List<String> getList() {
        return list;
    }

    public static List<String> getTexts() {
        return Arrays.asList(FD.text, CLASS_ADVICER.text, STUDENT_SUPPORT.text, JOB_SUPPORT.text, GET_STUDENT.text, REGION_SUPPORT.text);
    }
}
