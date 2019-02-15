package com.sminq.android.sminqassesment.repository.api

import com.sminq.android.sminqassesment.repository.api.model.APIResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Retrofit interface
 */
interface RestaurantAPI {

    @GET("json")
    fun getNearbyRestaurants(@QueryMap params:Map<String, String>): Observable<APIResponse>
}
