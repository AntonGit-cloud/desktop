package com.example.desktop.controllers.api;
/*
import com.example.app.api.API;
import com.example.app.models.Jwttoken;
import com.example.app.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthController implements Callback<Jwttoken> {

    static final String BASE_URL = "http://localhost:8080/api/";
    private Jwttoken jwttoken;
    private API api;
    private Retrofit retrofit;
    public Call<Jwttoken> call;

    public AuthController(User user){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
        //user body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
       call = api.registerUser(user);
       call.enqueue(this);
    }

    public void start() {
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Jwttoken> call, Response<Jwttoken> response) {
        if(response.isSuccessful()) {
            jwttoken = response.body();
            System.out.println(jwttoken.getToken());
            //changesList.forEach(change -> System.out.println(change.getProductName()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Jwttoken> call, Throwable throwable) {
        throwable.printStackTrace();
    }


    public API getApi() {
        return api;
    }
}
*/