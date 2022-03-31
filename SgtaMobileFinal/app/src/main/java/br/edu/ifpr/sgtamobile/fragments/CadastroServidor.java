package br.edu.ifpr.sgtamobile.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroServidor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroServidor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroServidor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroServidor.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroServidor newInstance(String param1, String param2) {
        CadastroServidor fragment = new CadastroServidor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
    List<String> roles;
    Cargo cargo;
    String matricula, cpf, nome, telefone, email, password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cadastro_servidor, container, false);

        edt_email = view.findViewById(R.id.edt_email);
   //     spinnerPerfil = view.findViewById(R.id.spinnerPerfil);
        edt_senha = view.findViewById(R.id.edt_senha);
        edt_matricula_servidor = view.findViewById(R.id.edt_matricula_servidor);
        edt_cpf_servidor = view.findViewById(R.id.edt_cpf_servidor);
        edt_nome_servidor = view.findViewById(R.id.edt_nome_servidor);
        edt_telefone_servidor = view.findViewById(R.id.edt_tel_servidor);
      //  spinnerCargo = view.findViewById(R.id.spinnerCargo);
        Spinner spinnerRole  = view.findViewById(R.id.spinnerPerfil);
        spinnerRole.setAdapter(new ArrayAdapter<Role>(getActivity(), android.R.layout.simple_spinner_item, Collections.singletonList(Role.ROLE_ADMIN)));

        Spinner spinnerCargos  = view.findViewById(R.id.spinnerCargo);
        spinnerCargos.setAdapter(new ArrayAdapter<Cargo>(getActivity(), android.R.layout.simple_spinner_item, Cargo.values()));

        btn_salvar=view.findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.i("inresponse","Responseunsuccessful Mehir");
               email = edt_email.getText().toString();
               roles = Collections.singletonList(spinnerRole.getSelectedItem().toString().trim());
               password = edt_senha.getText().toString();
               matricula = edt_matricula_servidor.getText().toString();
               cpf = edt_cpf_servidor.getText().toString();
               nome = edt_nome_servidor.getText().toString();
               telefone = edt_telefone_servidor.getText().toString();
               cargo = Cargo.valueOf(spinnerCargos.getSelectedItem().toString().trim());


                Log.i("inresponse","Responseunsuccessful ");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Utility.BASE_URL_USERS)
                        .client(Utility.getClientHttpForAll(getApplicationContext()))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServidorApiRestService service = retrofit.create(ServidorApiRestService.class);
                Log.i("inresponse","Responseunsuccessful Mehir");
                Servidor servidor = new Servidor(nome,cpf,telefone,matricula,cargo, new Usuario(email,password,roles));

                service.addServidor(servidor).enqueue(new Callback<Servidor>() {

                    @Override
                    public void onResponse(Call<Servidor> call, Response<Servidor> response) {

                        Servidor responseServidor = response.body();

                        if (!response.isSuccessful()){
                            Log.i("inresponse","Responseunsuccessful Mehir");
                        }
                        if (response.code()==200){
                            edt_cpf_servidor.getText().clear();
                            edt_matricula_servidor.getText().clear();
                            edt_nome_servidor.getText().clear();
                            edt_telefone_servidor.getText().clear();
                            edt_email.getText().clear();
                            edt_senha.getText().clear();
                         //   spinnerCargo.getText().clear();
                        //    spinnerPerfil.getText().clear();

                            Toast.makeText(getActivity(),"Client successfully added",Toast.LENGTH_SHORT).show();

                            Log.i("inresponse","mehir");
                        }
                    }

                    @Override
                    public void onFailure(Call<Servidor> call, Throwable t) {

                    }
                });

            }
        });

        return view;
    }

    private Context getApplicationContext() {
        return  null;
    }


}