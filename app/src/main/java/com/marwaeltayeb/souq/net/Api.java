package com.marwaeltayeb.souq.net;

import com.marwaeltayeb.souq.model.CartApiResponse;
import com.marwaeltayeb.souq.model.FavoriteApiResponse;
import com.marwaeltayeb.souq.model.History;
import com.marwaeltayeb.souq.model.HistoryApiResponse;
import com.marwaeltayeb.souq.model.Image;
import com.marwaeltayeb.souq.model.LoginApiResponse;
import com.marwaeltayeb.souq.model.NewsFeedResponse;
import com.marwaeltayeb.souq.model.OrderApiResponse;
import com.marwaeltayeb.souq.model.Ordering;
import com.marwaeltayeb.souq.model.Otp;
import com.marwaeltayeb.souq.model.ProductApiResponse;
import com.marwaeltayeb.souq.model.RegisterApiResponse;
import com.marwaeltayeb.souq.model.Review;
import com.marwaeltayeb.souq.model.ReviewApiResponse;
import com.marwaeltayeb.souq.model.Shipping;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

//    @POST("users/register")
//    @POST("register")
//    Call<RegisterApiResponse> createUser(@Body User user);

    @GET("register")
    Call<RegisterApiResponse> createUser(@Query("name") String name,@Query("email") String email, @Query("password") String password);

    @GET("users_login")
    Call<LoginApiResponse> logInUser(@Query("email") String email, @Query("password") String password);

    @DELETE("users/{userId}")
    Call<ResponseBody> deleteAccount(@Header("authorization") String token , @Path("userId") int userId);

//    @Multipart
//    @PUT("users_upload")
    @GET("users_upload")
    Call<ResponseBody> uploadPhoto(@Header("authorization") String token , @Part MultipartBody.Part userPhoto, @Part("id") RequestBody userId);

    @PUT("users/info")
    Call<ResponseBody> updatePassword(@Header("authorization") String token, @Query("password") String password, @Query("id") int userId);

    @Multipart
    @POST("products/insert")
    Call<ResponseBody> insertProduct(@Header("authorization") String token, @PartMap Map<String, RequestBody> productInfo, @Part MultipartBody.Part image);

    @GET("users/getImage")
    Call<Image> getUserImage(@Query("id") int userId);

    @GET("users/otp")
    Call<Otp> getOtp(@Header("authorization") String token, @Query("email") String email);

    @GET("products")
    Call<ProductApiResponse> getProductsByCategory(@Query("category") String category, @Query("userId") int userId,@Query("page") int page);

    @GET("search_products")
    Call<ProductApiResponse> searchForProduct(@Query("q") String keyword, @Query("userId") int userId);

//    @POST("favorites_add")
//    Call<ResponseBody> addFavorite(@Body Favorite favorite);
    @GET("favorites_add")
    Call<ResponseBody> addFavorite(@Query("userId") int userId, @Query("productId") int productId);

    @DELETE("favorites_remove")
    Call<ResponseBody> removeFavorite(@Query("userId") int userId, @Query("productId") int productId);

    @GET("favorites")
    Call<FavoriteApiResponse> getFavorites(@Query("userId") int userId);

    @GET("carts_add")
    Call<ResponseBody> addToCart(@Query("userId") int userId, @Query("productId") int productId);
//    @POST("carts_add")
//    Call<ResponseBody> addToCart(@Body Cart cart);

    @DELETE("carts_remove")
    Call<ResponseBody> removeFromCart(@Query("userId") int userId, @Query("productId") int productId);

    @GET("carts")
    Call<CartApiResponse> getProductsInCart(@Query("userId") int userId);

    @GET("history_add")
    Call<ResponseBody> addToHistory(@Query("userId") int userId, @Query("productId") int productId);

    @DELETE("history/remove")
    Call<ResponseBody> removeAllFromHistory();

    @GET("history")
    Call<HistoryApiResponse> getProductsInHistory(@Query("userId") int userId, @Query("page") int page);

    @POST("add_review")
    Call<ResponseBody> addReview(@Body Review review);

    @GET("add_review")
    Call<ResponseBody> addReview2(@Query("userId") int userId, @Query("productId") int productId
            , @Query("rate") double rate
            , @Query("feedback") String feedback
                                  );

    @GET("review")
    Call<ReviewApiResponse> getAllReviews(@Query("productId") int productId);

    @GET("posters")
    Call<NewsFeedResponse> getPosters();

    @GET("orders_get")
    Call<OrderApiResponse> getOrders(@Query("userId") int userId);

    @GET("address_add")
    Call<ResponseBody> addShippingAddress(@Query("address") String address,
                                          @Query("city") String city,
                                          @Query("country") String country,
                                          @Query("zip") String zip,
                                          @Query("phone") String phone,
                                          @Query("userId") int userId,
                                          @Query("productId") int productId);

    @GET("orders_add")
    Call<ResponseBody> orderProduct(
                                    @Query("nameOnCard") String nameOnCard,
                                    @Query("cardNumber") String cardNumber,
                                    @Query("fullDate") String fullDate,
                                    @Query("userId") int userId,
                                    @Query("productId") int productId);
}
