package br.edu.ifpr.sgtamobile.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.AlunoApiRestService;
import br.edu.ifpr.sgtamobile.api.CursoApiRestService;
import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Curso;
import br.edu.ifpr.sgtamobile.model.Disciplina;
import br.edu.ifpr.sgtamobile.model.Professor;
import br.edu.ifpr.sgtamobile.model.Responsavel;
import br.edu.ifpr.sgtamobile.model.Turno;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroCurso extends Fragment {

    EditText edt_disciplina_turma;
    EditText edt_professor_turma;
    EditText edt_ano_turma;
    Button btn_salvar_turma;
    EditText edt_curso_turma;
    Spinner edt_turno_turma;
    //EditText edt_turno_turma;
    List<Disciplina> diciplinas;
    List<Professor> professor;
    Disciplina diciplina;
    Turno turno;
    String ano, descricao;
    String dDescricao;
    String nome;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroCurso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroCurso.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroCurso newInstance(String param1, String param2) {
        CadastroCurso fragment = new CadastroCurso();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_curso, container, false);

        edt_ano_turma = view.findViewById(R.id.edt_ano_turma);
        edt_curso_turma = view.findViewById(R.id.edt_curso_turma);
        edt_disciplina_turma = view.findViewById(R.id.edt_disciplina_turma);
        edt_professor_turma = view.findViewById(R.id.edt_professor_turma);
        Spinner spinnerTurno  = view.findViewById(R.id.edt_turno_turma);
        spinnerTurno.setAdapter(new ArrayAdapter<Turno>(getActivity(), android.R.layout.simple_spinner_item, Turno.values()));

        btn_salvar_turma = view.findViewById(R.id.btn_salvar_turma);
        btn_salvar_turma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ano = edt_ano_turma.getText().toString();
                turno = Turno.valueOf(spinnerTurno.getSelectedItem().toString().trim());
                descricao = edt_curso_turma.getText().toString();
                dDescricao = edt_disciplina_turma.getText().toString();
                nome = edt_professor_turma.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.BASE_URL_USERS)
                .client(Utility.getClientHttpForAll(getApplicationContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CursoApiRestService service = retrofit.create(CursoApiRestService.class);

        Curso curso = new Curso(descricao,ano, turno , Collections.singletonList(new Disciplina(dDescricao, new Professor(nome))));

        service.addCurso(curso).enqueue(new Callback<Curso>() {

            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {

                Curso responseCurso = response.body();

                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful Mehir");
                }
                if (response.code()==200){
               //     edt_cpf.getText().clear();
                 //   edt_matricula.getText().clear();
                //    edt_nome_aluno.getText().clear();
              //      edt_tel_aluno.getText().clear();
               //     edt_responsavel.getText().clear();
              //      edt_tel_responsavel.getText().toString();

                    Toast.makeText(getActivity(),"Client successfully added",Toast.LENGTH_SHORT).show();

                    Log.i("inresponse","mehir");
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {

            }
        });
            }
        });

        return view;
    }

    private Context getApplicationContext() {
        return null;
    }

}