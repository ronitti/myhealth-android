package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.utils.DateTime;

public class FrequencyRegisterRequest {
    @SerializedName("type")
    private Integer type;
    @SerializedName("days_of_week")
    private Boolean daysOfWeek[];
    @SerializedName("custom_every")
    private Integer customEvery;
    @SerializedName("times_a_day")
    private Integer timesADay;
    @SerializedName("hours_of_day")
    private String hoursOfDay[];
    @SerializedName("start_date")
    private String startDate;

    public FrequencyRegisterRequest(Frequency frequency) {
        this.type = frequency.getFrequencyType().getType();
        this.daysOfWeek = frequency.getDaysOfWeek();
        this.customEvery = frequency.getCustomEvery();
        this.timesADay = frequency.getTimesADay();
        this.hoursOfDay = new String[frequency.getHoursOfDay().length];
        for(int i=0; i<frequency.getHoursOfDay().length; i++){
            this.hoursOfDay[i] = DateTime.SIMPLE_TIME_FORMAT.format(frequency.getHoursOfDay()[i]);
        }
        this.startDate = DateTime.SIMPLE_DATE_FORMAT.format(frequency.getStartDate());
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Boolean[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Integer getCustomEvery() {
        return customEvery;
    }

    public void setCustomEvery(Integer customEvery) {
        this.customEvery = customEvery;
    }

    public Integer getTimesADay() {
        return timesADay;
    }

    public void setTimesADay(Integer timesADay) {
        this.timesADay = timesADay;
    }

    public String[] getHoursOfDay() {
        return hoursOfDay;
    }

    public void setHoursOfDay(String[] hoursOfDay) {
        this.hoursOfDay = hoursOfDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
