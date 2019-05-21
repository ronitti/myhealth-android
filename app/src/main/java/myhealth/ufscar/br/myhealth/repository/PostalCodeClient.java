package myhealth.ufscar.br.myhealth.repository;

import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostalCodeClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://viacep.com.br/ws/";

    public static PostalCodeService getPostalCodeServiceInstance(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PostalCodeService.class);

    }


}
