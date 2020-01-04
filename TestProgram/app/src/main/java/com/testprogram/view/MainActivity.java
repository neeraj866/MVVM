package com.testprogram.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.testprogram.R;
import com.testprogram.database.AppDatabase;
import com.testprogram.database.CountryDao;
import com.testprogram.database.PurchaseDao;
import com.testprogram.model.CountriesModel;
import com.testprogram.model.PurchasesModel;
import com.testprogram.view_model.ItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel itemViewModel;
    private Spinner mSpManufacturerSpinner;
    private Spinner mSpCategorySpinner;
    private Spinner mSpCountrySpinner;
    private Spinner mSpStateSpinner;
    private Spinner mSpItemSpinner;

    private TextView mTxtManufacturerCost;
    private TextView mTxtCategoryCost;
    private TextView mTxtCountryCost;
    private TextView mTxtStatCost;
    private TextView mTxtItemQuantity;
    private TextView mTxtBuildingName;
    private PurchaseDao purchaseDao;
    private CountryDao countryDao;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db-items")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        purchaseDao = database.getPurchaseDao();
        countryDao = database.getCountriesDao();
        getData();
    }

    /**
     * initialization of views and others
     */
    private void initialization() {
        mSpManufacturerSpinner = findViewById(R.id.manufacturer_spinner);
        mSpCategorySpinner = findViewById(R.id.item_category_spinner);
        mSpCountrySpinner = findViewById(R.id.country_spinner);
        mSpStateSpinner = findViewById(R.id.state_spinner);
        mSpItemSpinner = findViewById(R.id.item_id_spinner);

        mTxtManufacturerCost = findViewById(R.id.manufacturer_price);
        mTxtCategoryCost = findViewById(R.id.category_price);
        mTxtCountryCost = findViewById(R.id.country_price);
        mTxtStatCost = findViewById(R.id.state_price);
        mTxtItemQuantity = findViewById(R.id.item_quantity);
        mTxtBuildingName = findViewById(R.id.building_name);
        // View Model
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);


    }

    /*
     * Fetch data from api/Database
     */
    private void getData() {
        if (purchaseDao.getPurchaseItems() != null && purchaseDao.getPurchaseItems().size() > 0) {
            setItemsData();
        } else {
            showProgressLoader();
            itemViewModel.getItemResponseLiveData().observe(this, itemResponses -> {
                if (itemResponses != null) {
                    int count = 0;
                    for (int i = 0; i < itemResponses.size(); i++) {

                        for (int j = 0; j < itemResponses.get(i).getUsageStatistics().getSessionInfos().size(); j++) {
                            for (int k = 0; k < itemResponses.get(i).getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {
                                count++;
                                PurchasesModel purchasesModel = new PurchasesModel();
                                purchasesModel.setId(count);
                                purchasesModel.setBuilding_id(itemResponses.get(i).getUsageStatistics().getSessionInfos().get(j).getBuilding_id());
                                purchasesModel.setManufacture(itemResponses.get(i).getManufacturer());
                                purchasesModel.setCost(itemResponses.get(i).getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                                purchasesModel.setItemCategoryId(itemResponses.get(i).getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemCategoryId());
                                purchasesModel.setItemId(itemResponses.get(i).getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemId());
                                purchaseDao.insert(purchasesModel); // Inserting into purchase table
                            }
                        }

                    }
                    setItemsData();
                    dismissLoader();
                }

            });
        }
        if (countryDao.getCountries() != null && countryDao.getCountries().size() > 0) {
            setCountriesData();
        } else {
            itemViewModel.getCountryResponseLiveData().observe(this, countryResponses -> {
                if (countryResponses != null) {

                    for (int i = 0; i < countryResponses.size(); i++) {
                        CountriesModel countriesModel = new CountriesModel();
                        countriesModel.setId(i + 1);
                        countriesModel.setBuilding_id(countryResponses.get(i).getBuilding_id());
                        countriesModel.setBuilding_name(countryResponses.get(i).getBuilding_name());
                        countriesModel.setCity(countryResponses.get(i).getCity());
                        countriesModel.setCountry(countryResponses.get(i).getCountry());
                        countriesModel.setState(countryResponses.get(i).getState());
                        countryDao.insert(countriesModel); // Inserting into country table
                    }
                    setCountriesData();
                }
            });
        }
    }

    /*
     * Fetching data from database and setting on to county spinner and state spinner
     */
    private void setCountriesData() {
        List<String> countries = countryDao.getCountriesName();
        List<String> state = new ArrayList<>();

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpCountrySpinner.setAdapter(countriesAdapter);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state.clear();
                state.addAll(countryDao.getStates(countries.get(position)));
                double price = purchaseDao.getCountriesTotal(countries.get(position));
                String total = "$" + String.format(Locale.getDefault(), "%.2f", price);
                mTxtCountryCost.setText(total);
                mSpStateSpinner.setAdapter(stateAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double price = purchaseDao.getStateTotal(state.get(position));
                String total = "$" + String.format(Locale.getDefault(), "%.2f", price);
                mTxtStatCost.setText(total);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /*
     * Fetching data from database and setting on to manufacturer spinner, category spinner,Item spinner
     */
    private void setItemsData() {

        List<String> manufacturers = purchaseDao.getManufacturers();
        List<String> item_category = purchaseDao.getItemCategory();
        List<String> item_id_list = purchaseDao.getItemId();


        ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, manufacturers);
        manufacturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpManufacturerSpinner.setAdapter(manufacturerAdapter);

        mSpManufacturerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double price = purchaseDao.getManufacturerTotal(manufacturers.get(position));
                String total = "$" + String.format(Locale.getDefault(), "%.2f", price);
                mTxtManufacturerCost.setText(total);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> itemCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item_category);
        itemCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpCategorySpinner.setAdapter(itemCategoryAdapter);

        mSpCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double price = purchaseDao.getItemCategoryTotal(item_category.get(position));
                String total = "$" + String.format(Locale.getDefault(), "%.2f", price);
                mTxtCategoryCost.setText(total);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> itemIdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item_id_list);
        itemIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpItemSpinner.setAdapter(itemIdAdapter);

        mSpItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int quantity = purchaseDao.getItemIdQuantity(item_id_list.get(position));

                mTxtItemQuantity.setText(String.valueOf(quantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTxtBuildingName.setText(countryDao.getBuildingName(purchaseDao.getBuildingId()));
    }

    /*
     * Showing progress dialog
     */
    private void showProgressLoader() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

    }

    /*
     * To dismiss the dialog
     */
    private void dismissLoader() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
