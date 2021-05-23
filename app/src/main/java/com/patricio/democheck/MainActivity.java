package com.patricio.democheck;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.patricio.democheck.ClassVO.LoginResponse;
import com.patricio.democheck.ClassVO.UploadResponse;
import com.patricio.democheck.Model.User;
import com.patricio.democheck.io.RestClient;
import com.patricio.democheck.io.RestMethods;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
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

        uploadDates("123", "ABC", "GU-00001", "Entrada",
                "17/08/2021", "3:00 PM", null);
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
    private void uploadDates(String claveDispositivo, String clavePlataforma, String numeroEmpleado, String tipoChecado,
                             String fecha, String horaDispositivo, Uri fotoUri) {

        //creating a file
        //File file = new File(getRealPathFromURI(fotoUri));
        String file = "Uri de ejemplo para el file";

        //creating request body for file
        RequestBody claveDispositivoBody = RequestBody.create(MediaType.parse("text/plain"), claveDispositivo);
        RequestBody clavePlataformaBody = RequestBody.create(MediaType.parse("text/plain"), clavePlataforma);
        RequestBody numeroEmpleadoBody = RequestBody.create(MediaType.parse("text/plain"), numeroEmpleado);
        RequestBody tipoChecadoBody = RequestBody.create(MediaType.parse("text/plain"), tipoChecado);
        RequestBody fechaBody = RequestBody.create(MediaType.parse("text/plain"), fecha);
        RequestBody horaDispositivoBody = RequestBody.create(MediaType.parse("text/plain"), horaDispositivo);
        RequestBody requestFile = RequestBody.create(MediaType.parse("text/plain"), file);
        //RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fotoUri)), file);


        restMethods.uploadDatos(claveDispositivoBody, clavePlataformaBody, numeroEmpleadoBody, tipoChecadoBody,fechaBody,
                horaDispositivoBody, requestFile).enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadResponse> call, @NonNull Response<UploadResponse> response) {
                if (response.isSuccessful()){
                    UploadResponse respuesta = response.body();
                    /*if (!respuesta.getError()){
                        respuesta.getUsuario();
                        ArrayList<User> usuario = new ArrayList<>();
                        usuario = respuesta.getUsuario();
                        Log.e("USer", "= "+usuario.get(0).getNombre()+ "Status: "+usuario.get(0).getStatus());
                        Toast.makeText(getApplicationContext(),"Usted es: "+usuario.get(0).getNombre()+" Su estatus es: "+usuario.get(0).getStatus(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
                    }*/
                    hideLoading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadResponse> call, @NonNull Throwable t) {
                t.printStackTrace();

                //Respuesta fallida
                Log.e(TAG, "Response: falla " + t.getMessage());

                hideLoading();
            }
        });
    }

}