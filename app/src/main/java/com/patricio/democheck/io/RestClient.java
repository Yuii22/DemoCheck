package com.patricio.democheck.io;

import android.util.Log;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RestClient {
    private static RestMethods API_SERVICE;

    public static RestMethods buildHTTPClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.42.217:8080/DemoCheck/web/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(RestMethods.class);
        }
        return API_SERVICE;
    }



    //Attach logging intercept to print Logs in LogCat
    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NonNull String message) {
                        Log.d("HTTP", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BODY);
        return httpLoggingInterceptor;
    }
}
