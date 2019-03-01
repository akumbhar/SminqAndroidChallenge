package com.sminq.android.sminqassesment.repository

import android.arch.lifecycle.LiveData
import com.sminq.android.sminqassesment.repository.api.RestaurantNetworkDataSource
import com.sminq.android.sminqassesment.repository.api.model.Restaurant
import com.sminq.android.sminqassesment.repository.db.RestaurantDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Repository class to manipulate data from various data sources i.e. network,database
 */
class RestaurantRepository(val networkDataSource: RestaurantNetworkDataSource, val restaurantDao: RestaurantDao) {

    public fun getRestaurantList(
        lat: Double,
        log: Double,
        apiKey: String,
        radius: Int,
        type: String
    ): LiveData<List<Restaurant>> {
        getRestaurantsListFromApi(lat, log, apiKey, radius, type)
        return getFactsFromDb()
    }


    private fun getRestaurantsListFromApi(lat: Double, log: Double, apiKey: String, radius: Int, type: String) {

        val restaurantAPI = networkDataSource.getNetworkData()
        var map = HashMap<String, String>();
        map.put("location", "$lat,$log")
        map.put("radius", "$radius")
        map.put("type", type)
        map.put("key", apiKey)
        restaurantAPI.getNearbyRestaurants(map).retry()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                clearCache()
                storeFactsInDb(data.results)
            }, { error ->
            })
    }

    private fun getFactsFromDb(): LiveData<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
    }

    private fun storeFactsInDb(facts: List<Restaurant>) {
        restaurantDao.insertAll(facts)
    }

    private fun clearCache() {
        restaurantDao.deleteAllFacts()
    }

}