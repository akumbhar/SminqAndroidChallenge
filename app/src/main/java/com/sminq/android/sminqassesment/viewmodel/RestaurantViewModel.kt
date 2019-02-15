package com.sminq.android.sminqassesment.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sminq.android.sminqassesment.repository.RestaurantRepository
import com.sminq.android.sminqassesment.repository.api.model.Restaurant

/**
 * ViewModel class that holds and maintains the data
 */
class RestaurantViewModel(restaurantRepository: RestaurantRepository) : ViewModel() {

    private lateinit var restaurantList: LiveData<List<Restaurant>>
    private val restaurantRepository: RestaurantRepository = restaurantRepository

    public fun fetchRestaurantList(
        lat: Double,
        log: Double,
        apiKey: String,
        radius: Int,
        type: String
    ): LiveData<List<Restaurant>> {

        return restaurantRepository.getRestaurantList(lat, log, apiKey, radius, type)
    }


}