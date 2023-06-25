package com.marwaeltayeb.souq.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marwaeltayeb.souq.model.Shipping;
import com.marwaeltayeb.souq.repository.ShippingRepository;

import okhttp3.ResponseBody;

public class ShippingViewModel  extends ViewModel {

    private final ShippingRepository shippingRepository;

    public ShippingViewModel() {
        shippingRepository = new ShippingRepository();
    }

    public LiveData<Shipping> addShippingAddress2(String address, String city, String country, String zip, String phone, int userId, int productId) {
        return shippingRepository.addShippingAddress3(address, city, country, zip, phone,userId, productId);
    }
}
