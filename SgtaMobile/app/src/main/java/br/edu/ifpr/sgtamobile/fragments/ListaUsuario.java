package br.edu.ifpr.sgtamobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.adapter.ItemAdapter;
import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaUsuario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaUsuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaUsuario newInstance(String param1, String param2) {
        ListaUsuario fragment = new ListaUsuario();
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
    TableLayout userstable;
    ListView myListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lista_usuario,container,false);
           viewAllbtn=view.findViewById(R.id.ViewAllbtn);
        userstable=view.findViewById(R.id.ViewAllClientTable);
        Log.i("inOncreate","were here");

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.100.182:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApiRestService userApi= retrofit.create(UserApiRestService.class);
        Call<List<User>> call = userApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful");
                }
                Log.i("inresponse","Response returned");
                List<User> users=response.body();

                myListView =(ListView) view.findViewById(R.id.mydataListView);
                ItemAdapter itemAdapter= new ItemAdapter(getActivity(),users);
                myListView.setAdapter(itemAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (t instanceof IOException){

                    Log.i("inresponse",t.getMessage());
                }

                else{
                    Log.i("inresponse","Failure conversion issue");
                }
            }
        });
        viewAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //description.setText("List of All Clients Viewed");

                viewAllbtn.setVisibility(View.GONE);
                userstable.setVisibility(View.VISIBLE);

            }
        });
        return view;
    }

}