package myhealth.ufscar.br.myhealth.data.collect.frequency;

public class Frequency {
    private FrequencyType frequencyType;
    private Boolean daysOfWeek[];
    private Integer daysOfMonth[];
    private Integer timesADay;

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Boolean[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Boolean[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Integer[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(Integer[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public Integer getTimesADay() {
        return timesADay;
    }

    public void setTimesADay(Integer timesADay) {
        this.timesADay = timesADay;
    }
}
