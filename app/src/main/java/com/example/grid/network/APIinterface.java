package com.example.grid.network;

import com.example.grid.model.Picsum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIinterface {
    @GET("v2/list")
    //call list in picsum
    Call<List<Picsum>> getPicsum();
}
