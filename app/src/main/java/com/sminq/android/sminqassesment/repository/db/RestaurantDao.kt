package com.sminq.android.sminqassesment.repository.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sminq.android.sminqassesment.repository.api.model.Restaurant

/**
 * Interface providing queries from database
 */
@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): LiveData<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(factList: List<Restaurant>)

    @Query("DELETE FROM restaurants")
    fun deleteAllFacts()


}