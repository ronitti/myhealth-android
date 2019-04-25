package myheatlh.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.Patient;
import myheatlh.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myheatlh.ufscar.br.myhealth.repository.MyHealthClient;
import myheatlh.ufscar.br.myhealth.repository.MyHealthService;
import myheatlh.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myheatlh.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import retrofit2.Response;

public class PatientLoad {
    public static Patient load(String susNumber){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<PatientLoadResponse> responseData;
        try {
            responseData = service.loadPatient(new PatientLoadRequest(susNumber)).execute();
            if(responseData.body() != null && !responseData.body().isSuccess()){
                throw new NonRegisteredUserException();
            }
            assert responseData.body() != null;
            return responseData.body().getPatientData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
