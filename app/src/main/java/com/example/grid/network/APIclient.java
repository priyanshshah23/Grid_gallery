package com.example.grid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

    //base url
    public static  final String base_url = "https://picsum.photos/";

    //retrofit
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
    public static APIinterface apIinterface(){
        return getClient().create(APIinterface.class);
    }
}
