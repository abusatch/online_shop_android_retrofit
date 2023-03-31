package com.marwaeltayeb.souq.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marwaeltayeb.souq.model.CartApiResponse2;
import com.marwaeltayeb.souq.net.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository2 {

    private static final String TAG = CartRepository2.class.getSimpleName();

    public LiveData<CartApiResponse2> getProductsInCart(int userId,int productId) {
        final MutableLiveData<CartApiResponse2> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().addToCart(userId,productId).enqueue(new Callback<CartApiResponse2>() {
            @Override
            public void onResponse(Call<CartApiResponse2> call, Response<CartApiResponse2> response) {
                Log.d(TAG, "onResponse: Succeeded");

                CartApiResponse2 cartApiResponse = response.body();

                if (response.body() != null) {
                    mutableLiveData.setValue(cartApiResponse);
                    Log.d(TAG, String.valueOf(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<CartApiResponse2> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
