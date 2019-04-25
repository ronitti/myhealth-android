package myheatlh.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.Patient;
import myheatlh.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myheatlh.ufscar.br.myhealth.repository.MyHealthClient;
import myheatlh.ufscar.br.myhealth.repository.MyHealthService;
import myheatlh.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class PatientRegister {
    public static boolean register(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<UserLoadResponse> responseData;
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
