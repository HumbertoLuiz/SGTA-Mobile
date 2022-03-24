package br.edu.ifpr.sgtamobile.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.model.Token;
import br.edu.ifpr.sgtamobile.model.User;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Toolbar tlbMainPage;

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnConnect;


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

                        Usuario usuario = new Usuario(email, password);
                        service.user(usuario).enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {

                                if (response.isSuccessful()) {

                                    Token responseToken = response.body();

                                    Utility.setPreferredToken(getApplicationContext(),responseToken.getToken());

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), responseToken.getToken(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Bad response!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {

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
