package myhealth.ufscar.br.myhealth.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHealthClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://myhealth-api.herokuapp.com/api/";

    public static MyHealthService getMyHealthServiceInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MyHealthService.class);
    }
}
