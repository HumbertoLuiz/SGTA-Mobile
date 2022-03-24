package br.edu.ifpr.sgtamobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.math.BigDecimal;


import br.edu.ifpr.sgtamobile.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Utility{


    public static String BASE_URL_USERS = "http://192.168.100.177:8080/";

 //   public static String BASE_URL_REGISTRATIONS = "http://192.168.100.107:8080/registration/api/";

 //   public static String BASE_URL_RECRUITMENTS = "http://192.168.100.107:8080/recrutment/api/";


    public static  int getDrawableResIdByNom(Context context, String resNom)  {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resNom , "drawable", pkgName);
        Log.i("Utility", "Resource Name : "+ resNom+"==> Resource ID = "+ resID);
        return resID;
    }



    public static String getPreferredToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_token_key),
                context.getString(R.string.pref_token_default));
    }
    public static void setPreferredToken(Context context, String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getString(R.string.pref_token_key), token);
        editor.commit();
    }

    public static OkHttpClient getClientHttpForAll(Context context) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        return builder.build();

    }




}
