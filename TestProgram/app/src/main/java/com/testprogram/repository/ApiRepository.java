package com.testprogram.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.testprogram.response.CountryResponse;
import com.testprogram.response.ItemResponse;
import com.testprogram.retrofit.ApiRequest;
import com.testprogram.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String TAG = ApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    /*
     * Calling GetAnalyticData api
     */
    public LiveData<List<ItemResponse>> getItems() {
        final MutableLiveData<List<ItemResponse>> data = new MutableLiveData<>();
        apiRequest.getItems()
                .enqueue(new Callback<List<ItemResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ItemResponse>> call, @NonNull Response<List<ItemResponse>> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<ItemResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onResponse response:: 22" + t.getMessage());

                        data.setValue(null);
                    }
                });
        return data;
    }

    /*
     * Calling GetBuildingData api
     */
    public LiveData<List<CountryResponse>> getCountries() {
        final MutableLiveData<List<CountryResponse>> data = new MutableLiveData<>();
        apiRequest.getCountries()
                .enqueue(new Callback<List<CountryResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<CountryResponse>> call, @NonNull Response<List<CountryResponse>> response) {
                        data.setValue(response.body());

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<CountryResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onResponse " + t.getMessage());

                        data.setValue(null);
                    }
                });
        return data;
    }
}
