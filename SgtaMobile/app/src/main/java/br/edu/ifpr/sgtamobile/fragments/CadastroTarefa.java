package br.edu.ifpr.sgtamobile.fragments;

import static android.R.layout.*;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.TarefaApiRestService;
import br.edu.ifpr.sgtamobile.model.Curso;
import br.edu.ifpr.sgtamobile.model.Disciplina;
import br.edu.ifpr.sgtamobile.model.Tarefa;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CadastroTarefa extends Fragment {


    EditText edt_local_tarefa, edt_descricao_tarefa, edt_inicio_tarefa, edt_termino_tarefa;
    Button btn_salvar;
    String descricao, local, dtCriacaoTarefa, dtFinalizacaoTarefa, ndescricao;
    List<Disciplina> disciplinaList;
    private ArrayList<String> getCursoNome = new ArrayList<String>();
    private ArrayList<String> getDisciplinaNome = new ArrayList<String>();
    int discId;
    private Spinner spinnerDisciplina;
    private Spinner spinnerCurso;

    public CadastroTarefa() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_tarefa2, container, false);

        edt_descricao_tarefa = (EditText) view.findViewById(R.id.edt_descricao_tarefa);
        edt_local_tarefa = (EditText) view.findViewById(R.id.edt_local_tarefa);
        edt_inicio_tarefa = (EditText) view.findViewById(R.id.edt_inicio_tarefa);
        edt_termino_tarefa = (EditText) view.findViewById(R.id.edt_termino_tarefa);

        spinnerDisciplina = view.findViewById(R.id.edt_disciplina_tarefa);;
        spinnerCurso = (Spinner) view.findViewById(R.id.edt_turma_tarefa);
        btn_salvar = (Button) view.findViewById(R.id.btn_salvar);



        get_Disciplina();
        return view;
    }

    private void get_Disciplina() {

        getDisciplinaNome.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.BASE_URL_USERS)
                .client(Utility.getClientHttpForAll(getApplicationContext()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        TarefaApiRestService service = retrofit.create(TarefaApiRestService.class);
        Call<String> call = service.getDisciplina();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //       Log.i("Success", response.body().toString());
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        Log.i("Success", response.body().toString());
                        try {

                            String getResponse = response.body().toString();
                            List<Disciplina> getDiscData = new ArrayList<Disciplina>();
                            Log.i("inresponse", "Responseunsuccessful Mehir");

                            JSONArray jsonArray = new JSONArray(getResponse);

                            Log.i("inresponse", "Responseunsuccessful ERROR AQUI");

                            getDiscData.add(new Disciplina(-1, "SELECT"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Disciplina disciplinas = new Disciplina();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                disciplinas.setDiscId(jsonObject.getInt("discId"));
                                disciplinas.setDescricao(jsonObject.getString("descricao"));
                                getDiscData.add(disciplinas);
                            }

                            for (int i = 0; i < getDiscData.size(); i++) {
                                getDisciplinaNome.add(getDiscData.get(i).getDescricao().toString());
                                getDisciplinaNome.add(String.valueOf(getDiscData.get(i).getDiscId()));
                            }
                            ArrayAdapter<String> spinDiscAdapter = new ArrayAdapter<String>(getContext(),
                                    simple_spinner_item, getDisciplinaNome);
                            spinDiscAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
                            spinnerDisciplina.setAdapter(spinDiscAdapter);
                            spinnerDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    //     int getdicsId = getDiscData.get(i).getDiscId( );
                                //    discId =Integer.parseInt(String.valueOf(spinnerDisciplina.getSelectedItem()));
                                    ndescricao = spinnerDisciplina.getItemAtPosition(i).toString();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

                btn_salvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        descricao = edt_descricao_tarefa.getText().toString();
                        local = edt_local_tarefa.getText().toString();
                        dtCriacaoTarefa = edt_inicio_tarefa.getText().toString();
                        dtFinalizacaoTarefa = edt_termino_tarefa.getText().toString();
                        ndescricao = String.valueOf(spinnerDisciplina.getSelectedItem());
                        discId = Integer.parseInt(String.valueOf(spinnerDisciplina.getSelectedItem()));


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Utility.BASE_URL_USERS)
                                .client(Utility.getClientHttpForAll(getApplicationContext()))
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        TarefaApiRestService service = retrofit.create(TarefaApiRestService.class);

                        Tarefa tarefa = new Tarefa(descricao, local, dtCriacaoTarefa, dtFinalizacaoTarefa, new Disciplina(discId));

                        service.addTarefa(tarefa).enqueue(new Callback<Tarefa>() {

                            @Override
                            public void onResponse(Call<Tarefa> call, Response<Tarefa> response) {

                                Tarefa responseTarefa = response.body();

                                if (!response.isSuccessful()) {
                                    Log.i("inresponse", "Responseunsuccessful Mehir");
                                }
                                if (response.code() == 200) {
                                    edt_descricao_tarefa.getText().clear();
                                    edt_inicio_tarefa.getText().clear();
                                    edt_local_tarefa.getText().clear();
                                    edt_termino_tarefa.getText().clear();


                                    Toast.makeText(getActivity(), "Client successfully added", Toast.LENGTH_SHORT).show();

                                    Log.i("inresponse", "mehir");
                                }
                            }

                            @Override
                            public void onFailure(Call<Tarefa> call, Throwable t) {

                            }
                        });

                    }


                });
            }


    public Context getApplicationContext() {
        return null;
    }
}


