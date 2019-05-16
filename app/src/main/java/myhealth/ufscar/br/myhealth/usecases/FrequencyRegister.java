package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateResponse;
import retrofit2.Response;

public class FrequencyRegister {
    public static boolean execute(PatientMonitoring patientMonitoring){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<FrequencyCreateResponse> frequencyResponse;
        Response<MonitoringCreateResponse> monitoringResponse;
        try {
            boolean hasNcd = false;
            for(int i = 0; i<patientMonitoring.getNcds().length; i++){
                if(patientMonitoring.getNcds()[i]){
                    hasNcd = true;
                    NCD ncd = patientMonitoring.getNcdFrequency().get(i).first;
                    Frequency frequency = patientMonitoring.getNcdFrequency().get(i).second;
                    frequencyResponse = service.createFrequency(new FrequencyCreateRequest(frequency)).execute();

                    assert frequencyResponse.body() != null;

                    Integer frequencyId = frequencyResponse.body().getFrequencyId();
                    String patientId = patientMonitoring.getPatient().getSusNumber();
                    Integer ncdId = ncd.getId();
                    monitoringResponse = service.createMonitor(new MonitoringCreateRequest(patientId,ncdId,frequencyId)).execute();

                    assert monitoringResponse.body() != null;
                }
            }
            return hasNcd;
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
