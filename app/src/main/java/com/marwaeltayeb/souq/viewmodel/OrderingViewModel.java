package com.marwaeltayeb.souq.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marwaeltayeb.souq.model.Ordering;
import com.marwaeltayeb.souq.repository.OrderingRepository;

import okhttp3.ResponseBody;

public class OrderingViewModel extends ViewModel {

    private final OrderingRepository orderingRepository;

    public OrderingViewModel(  ) {
        orderingRepository = new OrderingRepository();
    }

    public LiveData<ResponseBody> orderProduct(String nameOnCard, String cardNumber, String fullDate, int userId, int productId) {
        return orderingRepository.orderProduct(nameOnCard,cardNumber,fullDate,userId,productId);
    }
}
