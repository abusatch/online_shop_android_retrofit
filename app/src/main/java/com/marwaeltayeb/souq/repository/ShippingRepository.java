package com.marwaeltayeb.souq.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marwaeltayeb.souq.model.Shipping;
import com.marwaeltayeb.souq.net.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingRepository {

    private static final String TAG = ShippingRepository.class.getSimpleName();

    public LiveData<Shipping> addShippingAddress3(String address, String city, String country, String zip, String phone, int userId, int productId) {
        final MutableLiveData<Shipping> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().addShippingAddress(address, city, country, zip,  phone, userId, productId).enqueue(new Callback<Shipping>() {
            @Override
            public void onResponse(Call<Shipping> call, Response<Shipping> response) {
                Log.e(TAG, "onResponseee: " + response.body());

                Shipping shipping2 = response.body();

                if (response.body() != null) {
                    mutableLiveData.setValue(shipping2);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Shipping> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }




}
