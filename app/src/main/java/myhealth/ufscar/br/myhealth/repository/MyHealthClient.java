package myhealth.ufscar.br.myhealth.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHealthClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://myhealth-api.herokuapp.com/api/";
    //private static final String BASE_URL = "http://10.0.2.2:3000/api/";


    public static MyHealthService getMyHealthServiceInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor  = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MyHealthService.class);
    }
}
