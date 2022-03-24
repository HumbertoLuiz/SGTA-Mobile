package br.edu.ifpr.sgtamobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.ServidorApiRestService;
import br.edu.ifpr.sgtamobile.model.Cargo;
import br.edu.ifpr.sgtamobile.model.Role;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarUsuario extends AppCompatActivity {

    String[] permissao = {"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"};
    String[] carg = {"PROFESSOR", "PEDAGOGA", "ADMINISTRADOR"};
    EditText edt_email;
    EditText edt_senha;
    Spinner spinnerPerfil;
    EditText edt_matricula_servidor;
    EditText edt_cpf_servidor;
    EditText edt_nome_servidor;
    EditText edt_telefone_servidor;
    Spinner spinnerCargo ;
    Button btn_salvar;
    Usuario usuario;
    Role roles;
    Cargo cargo;
    String servidorId,matricula, cpf, nome, telefone, email, role, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        edt_matricula_servidor = findViewById(R.id.edt_matricula_servidor);
        edt_cpf_servidor = findViewById(R.id.edt_cpf_servidor);
        edt_nome_servidor = findViewById(R.id.edt_nome_servidor);
        edt_telefone_servidor = findViewById(R.id.edt_tel_servidor);

        Spinner cargos = findViewById(R.id.spinnerCargo);
        cargos.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter adapter = new ArrayAdapter(EditarUsuario.this, android.R.layout.simple_spinner_item,carg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargos.setAdapter(adapter);

        Spinner perfil = findViewById(R.id.spinnerPerfil);
        perfil.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter  adapter1 = new ArrayAdapter(EditarUsuario.this, android.R.layout.simple_spinner_item,permissao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        perfil.setAdapter(adapter1);

      /*  btn_salvar=findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("inresponse","Responseunsuccessful Mehir");
                email = edt_email.getText().toString();
                password = edt_senha.getText().toString();
                matricula = edt_matricula_servidor.getText().toString();
                cpf = edt_cpf_servidor.getText().toString();
                nome = edt_nome_servidor.getText().toString();
                telefone = edt_telefone_servidor.getText().toString();

                Log.i("inresponse","Responseunsuccessful ");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Utility.BASE_URL_USERS)
                        .client(Utility.getClientHttpForAll(getApplicationContext()))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServidorApiRestService service = retrofit.create(ServidorApiRestService.class);
                Log.i("inresponse","Responseunsuccessful Mehir");
               // Call<Void> call = service.EditServidor(servidorId,email,password,nome,matricula,cpf,telefone);
                call.enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (!response.isSuccessful()){
                            Log.i("inresponse","Responseunsuccessful Mehir");
                        }
                        if (response.code()==200){
                            edt_cpf_servidor.getText().clear();
                            edt_matricula_servidor.getText().clear();
                            edt_nome_servidor.getText().clear();
                            edt_telefone_servidor.getText().clear();
                            edt_email.getText().clear();
                            edt_senha.getText().toString();

                            Log.i("inresponse","mehir");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });*/

    }

    }
