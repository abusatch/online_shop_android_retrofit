package com.marwaeltayeb.souq.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.marwaeltayeb.souq.model.CartApiResponse2;


import com.marwaeltayeb.souq.repository.CartRepository2;

public class CartViewModel2 extends ViewModel {

    private final CartRepository2 cartRepository;

    public CartViewModel2() {
        cartRepository = new CartRepository2();
    }

    public LiveData<CartApiResponse2> getCartLiveData2(int userId,int productId) {
        return cartRepository.getProductsInCart(userId,productId);
    }
}
