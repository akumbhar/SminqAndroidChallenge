package com.sminq.android.sminqassesment.repository.api.model
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model class for API response
 */
data class APIResponse(
    val status: String,
    val nextPageToken: String,
    var results: List<Restaurant>
)


@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey(autoGenerate = true)
    val _id: Long = 0,
    //val geometry: Geometry,
    val icon: String,
    val id: String,
    val name: String,
   // val opening_hours: OpeningHours,
    val place_id: String,
    val price_level: Int,
    val rating: Double,
    val reference: String,
    val scope: String,
    //val types: List<String>,
    val user_ratings_total: Int,
    val vicinity: String
    //val lat:Double = geometry.location.lat,
    //val log:Double = geometry.location.lng,
    //val open_now: Boolean = opening_hours.open_now

)

data class Geometry(
    val location: Location

)

data class OpeningHours(
    val open_now: Boolean
)

data class Location(
    val lat: Double,
    val lng: Double
)


