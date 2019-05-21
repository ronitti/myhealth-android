package myhealth.ufscar.br.myhealth.repository;

import myhealth.ufscar.br.myhealth.repository.query.PostalCodeLoadResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostalCodeService {

    @GET("{cep}/json")
    Call<PostalCodeLoadResponse> getAddresByPostalCode(@Path("cep") String postalCode);

}
