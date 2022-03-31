package br.edu.ifpr.sgtamobile.api;

import java.util.List;

import br.edu.ifpr.sgtamobile.model.Aluno;
import br.edu.ifpr.sgtamobile.model.Curso;
import br.edu.ifpr.sgtamobile.model.Disciplina;
import br.edu.ifpr.sgtamobile.model.Tarefa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TarefaApiRestService {

    @GET("tarefas/")
    Call<List<Tarefa>> getTarefas();

    @POST("tarefas")
    Call<Tarefa> addTarefa(@Body Tarefa tarefa);

    @GET("cursos")
    Call<Disciplina> listaDisciplinas(@Query("Curso") String descricao, @Query("Disciplina") String mundescricaoicipio);

    @GET("disciplinas")
    Call<String> getDisciplina();

    @GET("cursos/{id}")
    Call<String> getDisciplinas(@Path("id")int id);

    @GET("cursos")
    Call<String> getCursos();

}
