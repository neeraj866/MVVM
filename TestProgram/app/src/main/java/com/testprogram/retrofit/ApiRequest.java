package com.testprogram.retrofit;


import com.testprogram.response.CountryResponse;
import com.testprogram.response.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("GetAnalyticData")
    Call<List<ItemResponse>> getItems();

    @GET("GetBuildingData")
    Call<List<CountryResponse>> getCountries();
}
