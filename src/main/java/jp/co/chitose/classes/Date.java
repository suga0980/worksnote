package jp.co.chitose.classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable {

    LocalDate localDate;

    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Date(int year, int month, int day) {
        this.localDate = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return localDate;
    }

    @Override
    public String toString() {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        return year + "年" + month + "月" + day + "日";
    }
}
