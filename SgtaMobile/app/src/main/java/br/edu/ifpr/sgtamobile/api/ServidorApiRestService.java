package br.edu.ifpr.sgtamobile.api;

import java.util.List;

import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Servidor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServidorApiRestService {

    @GET("servidores/")
    Call<List<Aluno>> getServidores();

    @GET("servidores/{field}/{searchfield}")
    Call<List<Servidor>> getServidorByField(@Path("field") String field, @Path("searchfield") String nome);

    @PUT("servidores/{id}")
    Call<Void> EditServidor(
            @Path("id") String id,
            @Query("name") String name,
            @Query("address") String address,
            @Query("phoneNumber") String phoneNumber
    );

    @DELETE("servidores/{id}")
    Call<Void> deleteServidor(@Path("id") String id);

    @POST("servidores")
    Call<Servidor> addServidor(@Body Servidor servidor);

}


