package br.edu.ifpr.sgtamobile.ui;

import static br.edu.ifpr.sgtamobile.model.Role.ROLE_ADMIN;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.fragments.PaginaAluno;
import br.edu.ifpr.sgtamobile.model.Role;
import br.edu.ifpr.sgtamobile.model.Token;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.model.UsuarioDados;
import br.edu.ifpr.sgtamobile.utils.SharedPreferencesUtils;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Toolbar tlbMainPage;

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnConnect;
    String usuario;
    //Role roles;
    String email, password,token,roles;
    UsuarioDados usuarioDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tlbMainPage = findViewById(R.id.tlb_main_page);
        //   setSupportActionBar(tlbMainPage);
        getSupportActionBar().setTitle("Gerenciamento de Tarefas");
        edtEmail = findViewById(R.id.edt_login_email);
        edtPassword = findViewById(R.id.edt_login_password);
        btnConnect = findViewById(R.id.btn_login_connect);


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(isOnline()){

                    if (edtEmail.getText().toString().matches("") ||
                            edtPassword.getText().toString().matches("") ) {

                        Toast.makeText(getApplicationContext(), "All Fields are required!!", Toast.LENGTH_LONG).show();

                    } else {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Utility.BASE_URL_USERS)
                                .client(Utility.getClientHttpForAll(getApplicationContext()))
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        UserApiRestService service = retrofit.create(UserApiRestService.class);

                        Usuario  usuario = new Usuario();
                        usuario.setEmail(email);
                        usuario.setPassword(password);

                        Log.i("inresponse","COMEÇA AQUI");

                        service.user(usuario).enqueue(new Callback<Token>() {
                            private Object UsuarioDados;
                            private String json;

                            @Override
                            public void onResponse(Call<Token> call, Response<Token> tokenResponse) {
                                if (tokenResponse.isSuccessful()) {
                                    SharedPreferences sp = SharedPreferencesUtils.get(getSharedPreferences("dados", 0));

                                    SharedPreferences.Editor editor = sp.edit();
                                    Map<String, String> data = new HashMap<>();

                                 //      String usuario = response.body();
                                    //   token = tokenResponse.body().getToken();
                                     //  Role = response.body().getRoles();


                                    // data.put("token", token);
                                    data.put("roles", new  Gson().toJson(roles));
                                  //  data.put("loggedUser", new Gson().toJson(response.body()));

                                    Log.i("inresponse",data.toString());

                                    Token token = tokenResponse.body();
                                //  Log.i("string", token.toString());

                                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                    Usuario user = new Usuario();
                                    usuario.setRoles(String.valueOf(roles));
                                    String json = gson.toJson(usuario);
                                    System.out.println(json);

                                    Log.i("tojson", usuario.toString());

                                    String a = gson.toJson(usuario);
                                    Log.i("string", a.toString());

                                        if (String.valueOf(usuario.getRoles()).equals("ROLE_ADMIN")){
                                            Log.i("inresponse","admin");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);

                                        }else if (String.valueOf(usuario.getRoles()).equals("ROLE_USER")){
                                            Log.i("inresponse","aluno");
                                            Intent intent = new Intent(getApplicationContext(), TarefaActivity.class);
                                            startActivity(intent);
                                        }
                                    Intent intent = new Intent(getApplicationContext(), AlunoPagina.class);
                                    startActivity(intent);

                                    Log.i("inresponse","SUCESSO LOGIM");
                                //    Toast.makeText(getApplicationContext(),String.valueOf(responseRole), Toast.LENGTH_SHORT).show();
                                    //     Toast.makeText(getApplicationContext(), usuario.getToken(), Toast.LENGTH_SHORT).show();

                                    Log.i("inresponse","FINAL LOGIN");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Bad response!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {
                                Log.i("inresponse","direto aqui");
                                //Snackbar.make(findViewById(R.id.rootLayout), "No Internet connection, Please set ON Internet connection!!!", Snackbar.LENGTH_SHORT).show();

                                Toast.makeText(getApplicationContext(), "Impossible to connect to the server !", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
                else{

                    Toast.makeText(getApplicationContext(), "No Internet connection, Please set ON Internet connection!!!", Toast.LENGTH_SHORT).show();
                        /*Snackbar.make(View, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                }
            }
        });


    }


    public boolean isOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()== NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()== NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }


}