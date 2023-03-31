package com.marwaeltayeb.souq.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marwaeltayeb.souq.model.Cart;
import com.marwaeltayeb.souq.model.CartApiResponse;
import com.marwaeltayeb.souq.model.CartApiResponse2;
import com.marwaeltayeb.souq.model.RegisterApiResponse;
import com.marwaeltayeb.souq.net.RetrofitClient;
import com.marwaeltayeb.souq.utils.RequestCallback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToCartRepository {

    private static final String TAG = ToCartRepository.class.getSimpleName();

    public LiveData<CartApiResponse2> addToCart(int userId, int productId) {
        Log.e(TAG, "klikcart: 123" );
        final MutableLiveData<CartApiResponse2> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().addToCart(userId,productId).enqueue(new Callback<CartApiResponse2>() {
            @Override
            public void onResponse(retrofit2.Call<CartApiResponse2> call, Response<CartApiResponse2> response) {
                           CartApiResponse2 cc = response.body();
//                if(response.code() == 200){
//                    callback.onCallBack();
//                }

                CartApiResponse2 ccc = response.body();
                Log.e(TAG , "onResponse okeeee" + ccc.getMessage());
                mutableLiveData.setValue(ccc);
                //ResponseBody responseBody = response.body();

                if (response.body() != null) {
                  //  mutableLiveData.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CartApiResponse2> call, Throwable t) {
                Log.d(TAG,"onFailure"  + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
