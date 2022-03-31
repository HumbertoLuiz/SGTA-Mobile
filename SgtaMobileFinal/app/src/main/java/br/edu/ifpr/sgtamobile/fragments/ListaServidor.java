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
import br.edu.ifpr.sgtamobile.adapter.ItemAdapter;
import br.edu.ifpr.sgtamobile.adapter.ServidorItemAdapter;
import br.edu.ifpr.sgtamobile.api.ServidorApiRestService;
import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.model.Servidor;
import br.edu.ifpr.sgtamobile.model.Usuario;
import br.edu.ifpr.sgtamobile.ui.EditarServidor;
import br.edu.ifpr.sgtamobile.ui.EditarUsuario;
import br.edu.ifpr.sgtamobile.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaServidor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaServidor extends Fragment {

    Intent myIntent;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaServidor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaServidor.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaServidor newInstance(String param1, String param2) {
        ListaServidor fragment = new ListaServidor();
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

    Button button2;
    TableLayout servidorstable;
    ListView myListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lista_servidor,container,false);
      //  bnt_Editar=view.findViewById(R.id.bnt_Editar);
        servidorstable=view.findViewById(R.id.ViewAllClientTable);


        Log.i("inOncreate","were here");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.BASE_URL_USERS)
                .client(Utility.getClientHttpForAll(getApplicationContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServidorApiRestService servidorApi= retrofit.create(ServidorApiRestService.class);
        Call<List<Servidor>> call = servidorApi.getServidores();
        call.enqueue(new Callback<List<Servidor>>() {
            @Override
            public void onResponse(Call<List<Servidor>> call, Response<List<Servidor>> response) {
                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful");
                }
                Log.i("inresponse","Response returned");
                List<Servidor> servidores=response.body();

                myListView =(ListView) view.findViewById(R.id.mydataListView);
                ServidorItemAdapter servidorItemAdapter = new ServidorItemAdapter(getActivity(),servidores);
                myListView.setAdapter(servidorItemAdapter);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                        int itemValue = (int) myListView.getItemAtPosition(i);
                      //  myIntent.putExtra("Selecione o servidor", itemValue);

                     //   Intent intent = new Intent(getActivity().getApplicationContext(), EditarServidor.class);

                 //       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //    startActivity(intent);

                    }
                });
         /*   bnt_Editar = view.findViewById(R.id.bnt_Editar);
            bnt_Editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 Intent intent = new Intent(getActivity().getApplicationContext(), EditarServidor.class);

                 startActivity(intent);
                }
            });*/
            }

            @Override
            public void onFailure(Call<List<Servidor>> call, Throwable t) {
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