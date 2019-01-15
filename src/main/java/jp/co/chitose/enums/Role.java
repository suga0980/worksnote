package jp.co.chitose.enums;

public enum Role {
    ADMIN("管理者"),
    STUFF("事務員"),
    TEACHER("教員"),
    ;

    private final String text;

    private Role(final String text) {
        this.text = text;
    }

    public String getString() {
        return text;
    }
}
