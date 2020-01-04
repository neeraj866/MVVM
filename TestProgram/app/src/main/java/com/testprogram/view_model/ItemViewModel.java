package com.testprogram.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.testprogram.repository.ApiRepository;
import com.testprogram.response.CountryResponse;
import com.testprogram.response.ItemResponse;

import java.util.List;


public class ItemViewModel extends AndroidViewModel {

    private ApiRepository apiRepository;
    private LiveData<List<ItemResponse>> itemResponseLiveData;
    private LiveData<List<CountryResponse>> countryResponseLiveData;

    public ItemViewModel(@NonNull Application application) {
        super(application);

        apiRepository = new ApiRepository();
        this.itemResponseLiveData = apiRepository.getItems();
        this.countryResponseLiveData = apiRepository.getCountries();
    }

    public LiveData<List<ItemResponse>> getItemResponseLiveData() {
        return itemResponseLiveData;
    }

    public LiveData<List<CountryResponse>> getCountryResponseLiveData() {
        return countryResponseLiveData;
    }
}
