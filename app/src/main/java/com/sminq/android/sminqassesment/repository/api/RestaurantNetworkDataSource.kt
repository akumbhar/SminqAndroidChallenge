package com.sminq.android.sminqassesment.repository.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Class to fetch data from API
 */
class RestaurantNetworkDataSource {
    companion object {
        val API_URL: String = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    }

    /**
     * Method return retrofit interface
     */
    public fun getNetworkData(): RestaurantAPI {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder();
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RestaurantNetworkDataSource.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();
        return retrofit.create(RestaurantAPI::class.java)
    }
}