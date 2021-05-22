package com.patricio.democheck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.patricio.democheck.ClassVO.LoginResponse;
import com.patricio.democheck.Model.User;
import com.patricio.democheck.io.RestClient;
import com.patricio.democheck.io.RestMethods;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    AppCompatEditText num_empleado;
    AppCompatButton loginButton;
    ProgressBar progressBar;
    NestedScrollView loginForm;
    RestMethods restMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restMethods = RestClient.buildHTTPClient();
        setContent();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    void setContent() {
        loginButton = findViewById(R.id.email_sign_in_button);
        num_empleado = findViewById(R.id.txtEmpleado);
        progressBar = findViewById(R.id.login_progress);
        loginForm = findViewById(R.id.login_form);
    }
    void doLogin() {

        showLoading();
        restMethods.login(num_empleado.getText().toString()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse respuesta = response.body();
                    if (respuesta.getEstado()){
                        respuesta.getUsuario();
                        ArrayList<User> usuario = new ArrayList<>();
                        usuario = respuesta.getUsuario();
                        Log.e("USer", "= "+usuario.get(0).getNombre()+ "Status: "+usuario.get(0).getStatus());
                        Toast.makeText(getApplicationContext(),"Usted es: "+usuario.get(0).getNombre()+" Su estatus es: "+usuario.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
                    }
                    hideLoading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                t.printStackTrace();

                //Respuesta fallida
                Log.e(TAG, "Response: falla " + t.getMessage());

                hideLoading();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
    }
}