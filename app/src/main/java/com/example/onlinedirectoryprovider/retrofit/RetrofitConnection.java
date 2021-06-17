package com.example.onlinedirectoryprovider.retrofit;

import com.example.onlinedirectoryprovider.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConnection {
    private static com.example.onlinedirectoryprovider.retrofit.RetrofitConnection connect;
    private ApiInterface clientService;

    public static synchronized com.example.onlinedirectoryprovider.retrofit.RetrofitConnection getInstance() {
        if (connect == null) {
            connect = new com.example.onlinedirectoryprovider.retrofit.RetrofitConnection();
        }
        return connect;
    }
    public ApiInterface createService() {

        Retrofit retrofit = null;

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }

        return retrofit.create(ApiInterface.class);

    }
}
