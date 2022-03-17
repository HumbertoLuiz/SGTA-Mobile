package br.edu.ifpr.sgtamobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.AlunoApiRestService;

import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Responsavel;
import br.edu.ifpr.sgtamobile.model.Role;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroAluno#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroAluno extends Fragment implements
        AdapterView.OnItemSelectedListener  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroAluno() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastrarUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroAluno newInstance(String param1, String param2) {
        CadastroAluno fragment = new CadastroAluno();
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



    String[] permissao = {"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"};
    EditText edt_email;
    EditText edt_senha;
    EditText edt_perfil;
    EditText edt_cpf;
    EditText edt_matricula;
    EditText edt_nome_aluno;
    EditText edt_tel_aluno;
    EditText edt_responsavel;
    EditText edt_tel_responsavel;
    Spinner spinnerPerfil;
    String email ,password,matricula, cpf,nomeAluno, telefoneAluno,nomeResponsavel,telefoneResponsavel, role;
    Usuario usuario;
    Role roles;
    Responsavel responsavel;
    Button btn_salvar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cadastro_aluno,container,false);
        edt_email = view.findViewById(R.id.edt_email);
        edt_senha = view.findViewById(R.id.edt_senha);
        edt_matricula = view.findViewById(R.id.edt_matricula);
        edt_cpf = view.findViewById(R.id.edt_cpf);
        edt_nome_aluno = view.findViewById(R.id.edt_nome_aluno);
        edt_tel_aluno = view.findViewById(R.id.edt_tel_aluno);
        edt_responsavel = view.findViewById(R.id.edt_responsavel);
        edt_tel_responsavel = view.findViewById(R.id.edt_tel_responsavel);

        Spinner perfil = view.findViewById(R.id.spinnerPerfil);
        perfil.setOnItemSelectedListener(this);
        ArrayAdapter  adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,permissao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        perfil.setAdapter(adapter);

        btn_salvar=view.findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email.getText().toString();

          //    perfil = spinnerPerfil.getText().toString();

                password = edt_senha.getText().toString();

                matricula = edt_matricula.getText().toString();

                cpf = edt_cpf.getText().toString();

                nomeAluno = edt_nome_aluno.getText().toString();

                telefoneAluno = edt_tel_aluno.getText().toString();

                nomeResponsavel = edt_responsavel.getText().toString();

                telefoneResponsavel = edt_tel_responsavel.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Utility.BASE_URL_USERS)
                        .client(Utility.getClientHttpForAll(getApplicationContext()))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AlunoApiRestService service = retrofit.create(AlunoApiRestService.class);

                Aluno aluno = new Aluno(nomeAluno,cpf,telefoneAluno,matricula,new Responsavel(nomeResponsavel,telefoneResponsavel), new Usuario(email,password,roles));

                service.addAluno(aluno).enqueue(new Callback<Aluno>() {

                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {

                        Aluno responseAluno = response.body();

                        if (!response.isSuccessful()){
                            Log.i("inresponse","Responseunsuccessful Mehir");
                        }
                        if (response.code()==200){
                            edt_cpf.getText().clear();
                            edt_matricula.getText().clear();
                            edt_nome_aluno.getText().clear();
                            edt_tel_aluno.getText().clear();
                            edt_responsavel.getText().clear();
                            edt_tel_responsavel.getText().toString();

                            Toast.makeText(getActivity(),"Client successfully added",Toast.LENGTH_SHORT).show();

                            Log.i("inresponse","mehir");
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> call, Throwable t) {

                    }
                });

            }
        });

        return view;
    }

    private Context getApplicationContext() {
        return null ;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    String string = permissao[i];
    //edt_tel_.setText(string);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}