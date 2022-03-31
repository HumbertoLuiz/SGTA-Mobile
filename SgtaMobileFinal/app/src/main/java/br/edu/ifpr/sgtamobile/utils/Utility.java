package br.edu.ifpr.sgtamobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.model.Token;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility{

    public static String BASE_URL_USERS = "http://192.168.100.217:8080/";

    public static  int getDrawableResIdByNom(Context context, String resNom)  {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resNom , "drawable", pkgName);
      //  Log.i("Utility", "Resource Name : "+ resNom+"==> Resource ID = "+ resID);
        return resID;
    }

  /*  public static String getPreferredToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_token_key),
                context.getString(R.string.pref_token_default));
    }*/
  /*  public static void setPreferredToken(Context context, String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getString(R.string.pref_token_key), token);
        editor.commit();
    }*/
  /*  public static void setPreferredRole(Context context, String nome) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getString(R.string.pref_role_key), nome);
        editor.commit();   }


    public static String getPreferredRole(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_role_key),
                context.getString(R.string.pref_role_default));
    }*/
    public static OkHttpClient getClientHttpForAll(Context context) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(loggingInterceptor);

        builder.addInterceptor(loggingInterceptor);

        return builder.build();

    }

    private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
     //   gsonBuilder.registerTypeAdapter(Token.class, new LoginDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }


}
