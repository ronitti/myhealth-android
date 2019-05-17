package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyData;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
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
                    RequestData requestData = new RequestData(new FrequencyData(frequency));
                    frequencyResponse = service.createFrequency(requestData).execute();

                    if(frequencyResponse.body() == null){
                        throw new NoConnectionException();
                    }
                    Log.i("API", frequencyResponse.body().getMessage().toString());
                    Log.i("API","CODE: " + frequencyResponse.body().getCode());

                    Integer frequencyId = frequencyResponse.body().getFrequencyId();
                    String patientId = patientMonitoring.getPatient().getSusNumber();
                    Integer ncdId = ncd.getId();
                    requestData = new RequestData(new MonitoringCreateRequest(patientId,ncdId,frequencyId));
                    monitoringResponse = service.createMonitor(requestData).execute();

                    if(monitoringResponse.body() == null){
                        throw new NoConnectionException();
                    }
                    Log.i("API", monitoringResponse.body().getMessage().toString());
                    Log.i("API","CODE: " + monitoringResponse.body().getCode());
                }
            }
            return hasNcd;
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
