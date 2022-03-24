package br.edu.ifpr.sgtamobile.api;

import java.util.List;

import br.edu.ifpr.sgtamobile.model.Token;
import br.edu.ifpr.sgtamobile.model.User;
import br.edu.ifpr.sgtamobile.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApiRestService {

  @GET("users/")
  Call<List<Usuario>> getUsers();


  @GET("users/{field}/{searchfield}")
  Call<List<User>> getUsersByField(@Path("field") String field,@Path("searchfield") String name);


  @PUT("users/{id}")
  Call<Void> EditUser(
          @Path("id") String id,
          @Query("name") String name,
          @Query("address") String address,
          @Query("phoneNumber") String phoneNumber
  );

  @DELETE("users/{id}")
  Call<Void> deleteClient(@Path("id") String id);

  @POST("users/")
  Call<Void> addUser(@Body User user);


  @POST("login")
    Call<Token> user (@Body Usuario usuario);

   // @GET("users")
  //  Call<List<UserApiRestService>> getUsers();


}
