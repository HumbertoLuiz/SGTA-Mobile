package br.edu.ifpr.sgtamobile.api;

import android.widget.Spinner;

import java.util.List;

import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Cargo;
import br.edu.ifpr.sgtamobile.model.Servidor;
import br.edu.ifpr.sgtamobile.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServidorApiRestService {

    @GET("servidores/")
    Call<List<Servidor>> getServidores();

    @GET("servidores/{field}/{searchfield}")
    Call<List<Servidor>> getServidorByField(@Path("field") String field, @Path("searchfield") String nome);

    @FormUrlEncoded
    @POST("servidores/{id}")
    Call<Void> EditServidor(
            @Path("id") String id,
            @Query("nome") String nome,
            @Query("matricula") String matricula,
            @Query("cpf") String cpf,
            @Query("telefone") String telefone);


    @DELETE("servidores/{id}")
    Call<Servidor> deleteServidor(@Path("id") int id);

    @FormUrlEncoded
    @POST("servidores")
    Call<Servidor> addServidor(@Body Servidor servidor);

    Call<Servidor> EditServidor(String nome, String matricula, String cpf, String telefone);

    //  Call<Void> EditServidor(String nome, String cpf, String telefone, String matricula, Cargo cargo, Usuario usuario);
}


