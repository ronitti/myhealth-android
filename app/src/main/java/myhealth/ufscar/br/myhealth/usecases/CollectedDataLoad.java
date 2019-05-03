package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterLoadResponse;
import retrofit2.Response;

public class CollectedDataLoad {
    public static List<Register> execute(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<List<RegisterLoadResponse>> registerResponse;
        try {
            registerResponse = service.listRegister(new RegisterListRequest(patient)).execute();
            assert registerResponse.body() != null;
            List<Register> registers = new ArrayList<>();
            for(RegisterLoadResponse registerLoadResponse: registerResponse.body())
                registers.add(registerLoadResponse.toRegister());
            return registers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
