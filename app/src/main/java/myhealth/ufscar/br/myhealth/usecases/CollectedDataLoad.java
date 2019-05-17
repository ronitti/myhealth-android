package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import retrofit2.Response;

public class CollectedDataLoad {
    public static List<Register> execute(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<RegisterListResponse> response;
        try {
            RequestData requestData = new RequestData(new RegisterListRequest(patient));
            response = service.listRegister(requestData).execute();
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
            return response.body().getRegisters();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
