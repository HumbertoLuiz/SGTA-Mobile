package br.edu.ifpr.sgtamobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;

import java.io.IOException;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.adapter.AlunoItemAdapter;
import br.edu.ifpr.sgtamobile.adapter.ItemAdapter;
import br.edu.ifpr.sgtamobile.api.AlunoApiRestService;
import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.ui.EditarAluno;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaAluno#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaAluno extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaAluno() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaAluno.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaAluno newInstance(String param1, String param2) {
        ListaAluno fragment = new ListaAluno();
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

    Button viewAllbtn;
    TableLayout alunostable;
    ListView myListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lista_aluno,container,false);
      //  viewAllbtn=view.findViewById(R.id.ViewAllbtn);
        alunostable=view.findViewById(R.id.ViewAllClientTable);
        Log.i("inOncreate","were here");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.BASE_URL_USERS)
                .client(Utility.getClientHttpForAll(getApplicationContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AlunoApiRestService alunoApi= retrofit.create(AlunoApiRestService.class);
        Call<List<Aluno>> call = alunoApi.getAlunos();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful");
                }
                Log.i("inresponse","Response returned");
                List<Aluno> aluno=response.body();

                myListView =(ListView) view.findViewById(R.id.mydataListView);
                AlunoItemAdapter alunoItemAdapter = new AlunoItemAdapter(getActivity(),aluno);
                myListView.setAdapter(alunoItemAdapter);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ListaAluno.this.getActivity(), EditarAluno.class);
                        //   intent.putExtras("usuario", users.get(i));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                if (t instanceof IOException){

                    Log.i("inresponse",t.getMessage());
                }

                else{
                    Log.i("inresponse","Failure conversion issue");
                }
            }
        });

        return view;
    }

    private Context getApplicationContext() {
        return  null;
    }

}