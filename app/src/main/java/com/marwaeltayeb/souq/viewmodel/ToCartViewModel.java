package com.marwaeltayeb.souq.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marwaeltayeb.souq.model.Cart;
import com.marwaeltayeb.souq.model.CartApiResponse;
import com.marwaeltayeb.souq.model.CartApiResponse2;
import com.marwaeltayeb.souq.repository.ToCartRepository;
import com.marwaeltayeb.souq.utils.RequestCallback;


import okhttp3.ResponseBody;
import retrofit2.Callback;

public class ToCartViewModel extends ViewModel {

    private final ToCartRepository toCartRepository;

    public ToCartViewModel() {
        toCartRepository = new ToCartRepository();
    }

    public LiveData<CartApiResponse2> addToCart(int userId, int productId) {
        return toCartRepository.addToCart(userId,productId);
    }
}
