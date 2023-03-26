package com.marwaeltayeb.souq.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marwaeltayeb.souq.model.Review;
import com.marwaeltayeb.souq.repository.WriteReview2Repository;
import com.marwaeltayeb.souq.repository.WriteReviewRepository;

import okhttp3.ResponseBody;

public class WriteReview2ViewModel extends ViewModel {

    private final WriteReview2Repository writeReview2Repository;

    public WriteReview2ViewModel() {
        writeReview2Repository = new WriteReview2Repository();
    }

    public LiveData<ResponseBody> writeReview2(int userId, int productId,
                                               double rate, String feedback
                                               ) {
        return writeReview2Repository.writeReview2(userId,productId,
        rate, feedback);
    }
}
