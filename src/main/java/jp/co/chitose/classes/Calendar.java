package jp.co.chitose.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar implements Serializable {

    LocalDate localDate;
    List<List<Date>> listFormattedCalendar = new ArrayList<>(6);

    public Calendar(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<List<Date>> getListFormatted() {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        localDate = LocalDate.of(year, month, 1);
        int dayOfWeekValue = localDate.getDayOfWeek().getValue();
        localDate = localDate.minusDays(dayOfWeekValue);
        for (int i = 0; i < 6; i++) {
            List<Date> listFormattedWeek = new ArrayList<>(7);
            for (int j = 0; j < 7; j++) {
                Date date = new Date(localDate);
                listFormattedWeek.add(date);
                localDate = localDate.plusDays(1);
            }
            listFormattedCalendar.add(listFormattedWeek);
        }
        return listFormattedCalendar;
    }
}
