package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.PatientRegisterResponse;
import retrofit2.Response;

public class PatientRegister {
    public static boolean register(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientRegisterResponse> responseData;
        try {
            responseData = service.createPatient(patient).execute();
            if(responseData.body() != null && !responseData.body().isSuccess()){
                throw new ExistingSusNumberException();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
