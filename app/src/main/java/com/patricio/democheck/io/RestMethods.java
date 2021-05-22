package com.patricio.democheck.io;

import com.patricio.democheck.ClassVO.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestMethods {

        //TODO Replace with your API's Login Method
        @GET("obtener_usuario.php")
        Call<LoginResponse> login(@Query("num_empleado") String num_empleado);
    }

