package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateResponse;
import retrofit2.Response;

public class CollectData {
    public static boolean execute(Patient patient, Register register){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<RegisterCreateResponse> registerResponse;
        try {
            registerResponse = service.createRegister(new RegisterCreateRequest(patient, register)).execute();
            assert registerResponse.body() != null;
            return registerResponse.body().isSuccess();
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
