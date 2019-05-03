package myhealth.ufscar.br.myhealth.data;

import android.support.v4.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

import myhealth.ufscar.br.myhealth.data.collect.frequency.DayOfWeek;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.data.collect.frequency.FrequencyType;

public class PatientMonitoring implements Serializable {
    private Patient patient;
    private boolean[] ncds;
    private ArrayList<Pair<NCD,Frequency>> ncdFrequency;

    public PatientMonitoring(){
        ncdFrequency = new ArrayList<>();
        ncds = new boolean[4];
        ncdFrequency.add(new Pair<>(NCD.HYPERTENSION, new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, 1)));
        ncdFrequency.add(new Pair<>(NCD.CORONARY_ARTERY_DISEASE, new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, 1)));
        ncdFrequency.add(new Pair<>(NCD.DIABETES, new Frequency(FrequencyType.DAILY, new DayOfWeek[]{DayOfWeek.EVERYDAY}, 4)));
        ncdFrequency.add(new Pair<>(NCD.OBESITY, new Frequency(FrequencyType.WEEKLY, new DayOfWeek[]{DayOfWeek.SUNDAY}, 1)));
    }

    public boolean[] getNcds() {
        return ncds;
    }

    public void setNcds(boolean[] ncds) {
        this.ncds = ncds;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ArrayList<Pair<NCD, Frequency>> getNcdFrequency() {
        return ncdFrequency;
    }

    public void setNcdFrequency(ArrayList<Pair<NCD, Frequency>> ncdFrequency) {
        this.ncdFrequency = ncdFrequency;
    }
}
