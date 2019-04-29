package myhealth.ufscar.br.myhealth.data.collect.frequency;

public class Frequency {
    public static final Frequency CARDIAC_DEFAULT = new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, new Integer[]{0}, 1);
    public static final Frequency DIABETES_DEFAULT = new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, new Integer[]{0}, 4);
    public static final Frequency OBESITY_DEFAULT = new Frequency(FrequencyType.WEEKLY, new DayOfWeek[]{DayOfWeek.SUNDAY}, new Integer[]{0}, 1);

    private FrequencyType frequencyType;
    private DayOfWeek daysOfWeek[];
    private Integer daysOfMonth[];
    private Integer timesADay;

    public Frequency(){

    }

    public Frequency(FrequencyType frequencyType, DayOfWeek[] daysOfWeek, Integer[] daysOfMonth, Integer timesADay) {
        this.frequencyType = frequencyType;
        this.daysOfWeek = daysOfWeek;
        this.daysOfMonth = daysOfMonth;
        this.timesADay = timesADay;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public DayOfWeek[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek[] daysOfWeek) {
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
