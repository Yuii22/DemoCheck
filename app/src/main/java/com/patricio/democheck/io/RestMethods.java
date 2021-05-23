package com.patricio.democheck.io;

import com.patricio.democheck.ClassVO.LoginResponse;
import com.patricio.democheck.ClassVO.UploadResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RestMethods {

        //TODO Replace with your API's Login Method
        @POST("empleado.php")
        Call<LoginResponse> login(@Query("num_empleado") String num_empleado);

        @Multipart
        @POST("checado.php")
        Call<UploadResponse> uploadDatos(@Part("claveDispositivo") RequestBody claveDispositivo,
                                         @Part("clavePlataforma") RequestBody clavePlataforma,
                                         @Part("numeroEmpleado") RequestBody numeroEmpleado,
                                         @Part("tipoChecado") RequestBody tipoChecado,
                                         @Part("fecha") RequestBody fecha,
                                         @Part("horaDispositivo") RequestBody horaDispositivo,
                                         @Part("latitud") RequestBody latitud,
                                         @Part("longitud") RequestBody longitud,
                                         @Part("foto\"; filename=\"myfile.jpg\" ") RequestBody foto);


}

