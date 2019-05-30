package myhealth.ufscar.br.myhealth.usecases;

import android.support.v4.util.Pair;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringDataFull;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringListRequest;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringListResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import retrofit2.Response;

public class GetMonitoringData {
    public static PatientMonitoring execute(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<MonitoringListResponse> monitoringResponse;
        try {
            RequestData requestData = new RequestData(new MonitoringListRequest(patient.getSusNumber()));
            monitoringResponse = service.listMonitorByPatient(requestData).execute();

            if(monitoringResponse.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", monitoringResponse.body().getMessage().toString());
            Log.i("API","CODE: " + monitoringResponse.body().getCode());

            PatientMonitoring pm = new PatientMonitoring();
            ArrayList<Pair<NCD, Frequency>> monitorings = new ArrayList<>();
            boolean[] ncds = new boolean[4];

            for (MonitoringDataFull monitor : monitoringResponse.body().getMonitoring()){
                monitorings.add(new Pair<>(NCD.values()[monitor.getNcdId()], monitor.getFrequency().toFrequency()));
                ncds[monitor.getNcdId()] = true;
            }
            pm.setPatient(patient);
            pm.setNcdFrequency(monitorings);
            pm.setNcds(ncds);

            return pm;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
