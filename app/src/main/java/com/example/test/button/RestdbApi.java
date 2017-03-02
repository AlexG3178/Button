package com.example.test.button;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface RestdbApi {

    @Headers("x-apikey: 32770eaca817b017cf400417713303aed4981")
    @GET("rest/buttoncollection?q={\"name\" : \"Alex\"}")
    Call<List<User>> getAllUsers ();

}
