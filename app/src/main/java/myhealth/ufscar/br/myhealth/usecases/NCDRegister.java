package myhealth.ufscar.br.myhealth.usecases;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;

public class NCDRegister {
    public static void register(PatientMonitoring patientMonitoring, NCD ncd, boolean monitor){
        patientMonitoring.getNcds()[ncd.getId()] = monitor;
    }
}
