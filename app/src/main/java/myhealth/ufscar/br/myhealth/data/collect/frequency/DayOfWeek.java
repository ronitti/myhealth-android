package myhealth.ufscar.br.myhealth.data.collect.frequency;

import myhealth.ufscar.br.myhealth.R;

public enum DayOfWeek {
    EVERYDAY(R.string.lbl_every_day, 0),
    SUNDAY(R.string.lbl_sunday, 1),
    MONDAY(R.string.lbl_monday, 2),
    TUESDAY(R.string.lbl_tuesday, 3),
    WEDNESDAY(R.string.lbl_wednesday, 4),
    THURSDAY(R.string.lbl_thursday, 5),
    FRIDAY(R.string.lbl_friday,6),
    SATURDAY(R.string.lbl_saturday, 7);

    Integer dayOfWeek;
    Integer day;
    DayOfWeek(Integer dayOfWeek, Integer day){
        this.dayOfWeek = dayOfWeek;
        this.day = day;
    }
}
