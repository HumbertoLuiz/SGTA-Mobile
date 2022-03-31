package br.edu.ifpr.sgtamobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.AlunoApiRestService;
import br.edu.ifpr.sgtamobile.api.ApiClient;
import br.edu.ifpr.sgtamobile.api.ServidorApiRestService;
import br.edu.ifpr.sgtamobile.model.Cargo;
import br.edu.ifpr.sgtamobile.model.Role;
import br.edu.ifpr.sgtamobile.model.Servidor;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarServidor extends FragmentActivity {

    EditText edt_email;
    EditText edt_senha;
    Spinner spinnerPerfil;
    EditText edt_matricula_servidor;
    EditText edt_cpf_servidor;
    EditText edt_nome_servidor;
    EditText edt_telefone_servidor;
    Spinner spinnerCargo;
    Button btn_salvar_editar;
    Usuario usuario;
    Role roles;
    Cargo cargo;
    Servidor servidor;
    String Id, matricula, cpf, nome, telefone, email, role, password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servidor);

        //Listar
        btn_salvar_editar=findViewById(R.id.btn_salvar_editar);
        btn_salvar_editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Utility.BASE_URL_USERS)
                        .client(Utility.getClientHttpForAll(getApplicationContext()))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServidorApiRestService service = retrofit.create(ServidorApiRestService.class);
                Call<List<Servidor>> call = service.getServidores();
                call.enqueue(new Callback<List<Servidor>>() {
                    @Override
                    public void onResponse(Call<List<Servidor>> call, Response<List<Servidor>> response) {
                        List<Servidor> servidorInfo = response.body();

                        for (int i = 0; i < servidorInfo.size(); i++) {
                            Toast.makeText(EditarServidor.this, servidorInfo.get(i).getNome(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Servidor>> call, Throwable t) {

                    }
                });
            }
        });
        //delete
        btn_salvar_editar=findViewById(R.id.btn_salvar_editar);
        btn_salvar_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ServidorApiRestService service =  ApiClient.getClient().create(ServidorApiRestService.class);
                Servidor servidor = new Servidor();

                servidor.setNome(edt_nome_servidor.getText().toString());
                servidor.setMatricula(edt_matricula_servidor.getText().toString());
                servidor.setCpf(edt_cpf_servidor.getText().toString());
                servidor.setTelefone(edt_telefone_servidor.getText().toString());

                Call<Servidor> call1 = service.deleteServidor(servidor.getId());
                call1.enqueue(new Callback<Servidor>() {
                    @Override
                    public void onResponse(Call<Servidor> call, Response<Servidor> response) {
                        Toast.makeText(EditarServidor.this, "response" + response.body().getNome(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Servidor> call, Throwable t) {
                        Log.i("Hello", "" + t);
                        Toast.makeText(EditarServidor.this, "Throwable" + t, Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        //Update
  /*      btn_salvar_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Utility.BASE_URL_USERS)
                        .client(Utility.getClientHttpForAll(getApplicationContext()))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServidorApiRestService service = retrofit.create(ServidorApiRestService.class);
                Servidor servidor = new Servidor();
                servidor.getNome(edt_nome_servidor.getText().toString());
                servidor.getMatricula(edt_matricula_servidor.getText().toString());
                servidor.getCpf(edt_cpf_servidor.getText().toString());
                servidor.setTelefone(edt_telefone_servidor.getText().toString());

                Call<Servidor> call =
                        service.EditServidor(servidor.getNome(), servidor.getMatricula(), servidor.getCpf(),
                                servidor.getTelefone());
                call.enqueue(new Callback<Servidor>() {

                    @Override
                    public void onResponse(Call<Servidor> call, Response<Servidor> response) {

                    }

                    @Override
                    public void onFailure(Call<Servidor> call, Throwable t) {

                    }
                });

            }
        });*/
    }
}










