package br.edu.ifpr.sgtamobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifpr.sgtamobile.api.UserApiRestService;
import br.edu.ifpr.sgtamobile.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import br.edu.ifpr.sgtamobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastrarUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarUsuario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastrarUsuario() {
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
    public static CadastrarUsuario newInstance(String param1, String param2) {
        CadastrarUsuario fragment = new CadastrarUsuario();
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
    EditText edt_email_user;
    EditText edt_senha_user;
    String email ,password;
    Button btn_salvar_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cadastrar_usuario,container,false);
        edt_email_user = view.findViewById(R.id.edt_email_user);
        edt_senha_user= view.findViewById(R.id.edt_senha_user);

        btn_salvar_user=view.findViewById(R.id.btn_salvar_user);
        btn_salvar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email_user.getText().toString();
                password = edt_senha_user.getText().toString();

                User user = new User(email,password);

                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("http://192.168.100.182:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserApiRestService userApi= retrofit.create(UserApiRestService.class);
                Call<Void> call = userApi.addUser(user);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()){
                            Log.i("inresponse","Responseunsuccessful");
                        }
                        if (response.code()==200){
                           edt_email_user.getText().clear();
                            edt_senha_user.getText().clear();

                            Toast.makeText(getActivity(),"Client successfully added",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

        return view;
    }
}