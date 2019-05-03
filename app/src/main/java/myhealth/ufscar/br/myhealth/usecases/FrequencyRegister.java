package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyRegisterRequest;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyRegisterResponse;
import myhealth.ufscar.br.myhealth.repository.query.NCDRegisterRequest;
import myhealth.ufscar.br.myhealth.repository.query.NCDRegisterResponse;
import retrofit2.Response;

public class FrequencyRegister {
    public static boolean execute(PatientMonitoring patientMonitoring){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<FrequencyRegisterResponse> frequencyResponse;
        Response<NCDRegisterResponse> ncdResponse;
        try {
            boolean hasNcd = false;
            for(int i = 0; i<patientMonitoring.getNcds().length; i++){
                if(patientMonitoring.getNcds()[i]){
                    hasNcd = true;
                    NCD ncd = patientMonitoring.getNcdFrequency().get(i).first;
                    Frequency frequency = patientMonitoring.getNcdFrequency().get(i).second;
                    frequencyResponse = service.createFrequency(new FrequencyRegisterRequest(frequency)).execute();

                    assert frequencyResponse.body() != null;

                    Integer frequencyId = frequencyResponse.body().getFrequencyId();
                    String patientId = patientMonitoring.getPatient().getSusNumber();
                    Integer ncdId = ncd.getId();
                    ncdResponse = service.createMonitor(new NCDRegisterRequest(patientId,ncdId,frequencyId)).execute();

                    assert ncdResponse.body() != null;
                }
            }
            return hasNcd;
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
