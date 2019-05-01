package myhealth.ufscar.br.myhealth.data.collect.frequency;

import java.util.Date;

public class Frequency {
    public static final Frequency CARDIAC_DEFAULT = new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, 1);
    public static final Frequency DIABETES_DEFAULT = new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, 4);
    public static final Frequency OBESITY_DEFAULT = new Frequency(FrequencyType.WEEKLY, new DayOfWeek[]{DayOfWeek.SUNDAY}, 1);

    private FrequencyType frequencyType;
    private Boolean daysOfWeek[];
    private Integer customEvery = 1;
    private Integer timesADay;
    private Date startDate;
    private Date hoursOfDay[];

    public Date[] getHoursOfDay() {
        return hoursOfDay;
    }

    public void setHoursOfDay(Date[] hoursOfDay) {
        this.hoursOfDay = hoursOfDay;
    }

    public Frequency(){
        daysOfWeek = new Boolean[8];
    }

    public Frequency(FrequencyType frequencyType, DayOfWeek[] daysOfWeek, Integer timesADay) {
        this.frequencyType = frequencyType;
        this.daysOfWeek = new Boolean[8];
        for(DayOfWeek dayOfWeek : daysOfWeek) {
            this.daysOfWeek[dayOfWeek.getDay()] = true;
        }
        this.timesADay = timesADay;
        startDate = new Date();
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
        if(frequencyType == FrequencyType.DAILY){
            daysOfWeek = new Boolean[8];
            daysOfWeek[DayOfWeek.EVERYDAY.getDay()] = true;
        }
    }

    public Boolean[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Boolean[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Integer getTimesADay() {
        return timesADay;
    }

    public void setTimesADay(Integer timesADay) {
        this.timesADay = timesADay;
    }

    public Integer getCustomEvery() {
        return customEvery;
    }

    public void setCustomEvery(Integer customEvery) {
        this.customEvery = customEvery;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
