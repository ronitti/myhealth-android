package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import myhealth.ufscar.br.myhealth.data.collect.Register;

public class RegisterListResponse extends ResponseData {

    @SerializedName("registers")
    private List<RegisterData> registers;



    public List<Register> getRegisters() {
        List<Register> registersList = new ArrayList<>();
        for(RegisterData registerData : registers)
            registersList.add(registerData.toRegister());
        return registersList;
    }
}
