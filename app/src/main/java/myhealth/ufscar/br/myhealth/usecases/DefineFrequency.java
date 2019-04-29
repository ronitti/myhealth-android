package myhealth.ufscar.br.myhealth.usecases;

import android.util.Pair;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;

public class DefineFrequency {
    public void execute(PatientMonitoring patientMonitoring, NCD ncd, Frequency frequency){
        patientMonitoring.getNcdFrequency().set(ncd.getId(),new Pair<>(ncd, frequency));
    }
}
