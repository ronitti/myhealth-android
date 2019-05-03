package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientRegisterResponse;
import retrofit2.Response;

public class PatientLoad {
    public static Patient load(User user){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientRegisterResponse> responseData;
        try {
            responseData = service.loadPatientByUser(new PatientLoadRequest(user)).execute();
            if(responseData.body() != null && !responseData.body().isSuccess()){
                throw new NonRegisteredUserException();
            }
            assert responseData.body() != null;
            System.out.println("NOme" + responseData.body().getPatientData().getName());
            return responseData.body().getPatientData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Patient load(String susNumber){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientRegisterResponse> responseData;
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
