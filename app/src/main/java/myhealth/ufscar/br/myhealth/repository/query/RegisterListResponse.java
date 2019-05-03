package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterListResponse extends ResponseData {
    @SerializedName("registers")
    private List<RegisterLoadResponse> registers;

    public List<RegisterLoadResponse> getRegisters() {
        return registers;
    }
}
