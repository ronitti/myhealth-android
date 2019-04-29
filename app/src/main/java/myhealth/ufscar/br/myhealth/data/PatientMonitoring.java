package myhealth.ufscar.br.myhealth.data;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;

public class PatientMonitoring implements Serializable {
    private Patient patient;
    private boolean[] ncds;
    private ArrayList<Pair<NCD,Frequency>> ncdFrequency;

    public PatientMonitoring(){
        ncdFrequency = new ArrayList<>();
        ncds = new boolean[4];
        ncdFrequency.add(new Pair<>(NCD.HYPERTENSION, Frequency.CARDIAC_DEFAULT));
        ncdFrequency.add(new Pair<>(NCD.CORONARY_ARTERY_DISEASE, Frequency.CARDIAC_DEFAULT));
        ncdFrequency.add(new Pair<>(NCD.DIABETES, Frequency.DIABETES_DEFAULT));
        ncdFrequency.add(new Pair<>(NCD.OBESITY, Frequency.OBESITY_DEFAULT));
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
