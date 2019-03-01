package com.sminq.android.sminqassesment

import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sminq.android.sminqassesment.view.RestaurantListFragment


class MainActivity : AppCompatActivity() {

    companion object {
        val MY_PERMISSIONS_REQUEST_LOCATION = 101
        val TAG:String = MainActivity.javaClass.simpleName
        val INTERVAL = 1000.toLong() // In milliseconds
        val DISTANCE = 10.toFloat() // In meters

        val locationListeners = arrayOf(
            LTRLocationListener(LocationManager.GPS_PROVIDER),
            LTRLocationListener(LocationManager.NETWORK_PROVIDER)
        )


        class LTRLocationListener(provider: String) : android.location.LocationListener {

            val lastLocation = Location(provider)
            override fun onLocationChanged(location: Location?) {
                lastLocation.set(location)
            }

            override fun onProviderDisabled(provider: String?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }
        }

    }

    private lateinit var  mLocationManager : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, RestaurantListFragment()).commit()
        }
        mLocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    }



    private fun startLocationUpdates() {

        try {

            mLocationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                INTERVAL,
                DISTANCE,
                locationListeners[1]
            )

        } catch (e: SecurityException) {

            Log.e(TAG, "Fail to request location update", e)

        } catch (e: IllegalArgumentException) {

            Log.e(TAG, "Network provider does not exist", e)

        }



        try {
            mLocationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                INTERVAL,
                DISTANCE,
                locationListeners[0]
            )

        } catch (e: SecurityException) {

            Log.e(TAG, "Fail to request location update", e)

        } catch (e: IllegalArgumentException) {

            Log.e(TAG, "GPS provider does not exist", e)

        }
    }

    private fun stopLocationUpdates() {

        if (mLocationManager != null)

            for (i in 0..locationListeners.size) {
                try {
                    mLocationManager?.removeUpdates(locationListeners[i])
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to remove location listeners")
                }
            }

    }
}
