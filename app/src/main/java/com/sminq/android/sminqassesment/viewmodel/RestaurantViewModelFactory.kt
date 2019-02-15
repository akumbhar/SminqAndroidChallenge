package com.sminq.android.sminqassesment.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sminq.android.sminqassesment.repository.RestaurantRepository
import com.sminq.android.sminqassesment.repository.api.RestaurantNetworkDataSource
import com.sminq.android.sminqassesment.repository.db.AppDatabase

/**
 * Factory class that initialize ViewModel constructor parameters
 */
class RestaurantViewModelFactory(private val context: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var repository = RestaurantRepository(RestaurantNetworkDataSource(),
            AppDatabase.getInstance(context)!!.restaurantDao());
        return RestaurantViewModel(repository) as T
    }
}