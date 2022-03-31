package br.edu.ifpr.sgtamobile.api;

import java.util.List;

import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Token;
import br.edu.ifpr.sgtamobile.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlunoApiRestService {

    @GET("alunos/")
    Call<List<Aluno>> getAlunos();

    @GET("alunos/{field}/{searchfield}")
    Call<List<Aluno>> getAlunoByField(@Path("field") String field, @Path("searchfield") String nome);

    @PUT("alunos/{id}")
    Call<Void> EditUser(
            @Path("id") String id,
            @Query("name") String name,
            @Query("address") String address,
            @Query("phoneNumber") String phoneNumber
    );

    @DELETE("alunos/{id}")
    Call<Void> deleteClient(@Path("id") String id);

    @POST("alunos")
    Call<Aluno> addAluno(@Body Aluno aluno);

}

