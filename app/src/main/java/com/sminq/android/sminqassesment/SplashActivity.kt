package com.sminq.android.sminqassesment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

class SplashActivity : AppCompatActivity() {

    private lateinit var mLocationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkLocationPermission()
        init()

    }

    private fun init() {

        mLocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    }


    private fun checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(
                    this@SplashActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MainActivity.MY_PERMISSIONS_REQUEST_LOCATION
                )

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MainActivity.MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
        } else {

            showToast("Permission already granted")

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MainActivity.MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //permission granted
                } else {
                    finish()
                    showToast("This app won't work without location permission")
                }
                return
            }
        }
    }


    fun showToast(text: String) {

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


    companion object {
        val MY_PERMISSIONS_REQUEST_LOCATION = 101
        val TAG: String = MainActivity.javaClass.simpleName
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

    private fun startLocationUpdates() {

        try {

            mLocationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MainActivity.INTERVAL,
                MainActivity.DISTANCE,
                MainActivity.locationListeners[1]
            )

        } catch (e: SecurityException) {

            Log.e(MainActivity.TAG, "Fail to request location update", e)

        } catch (e: IllegalArgumentException) {

            Log.e(MainActivity.TAG, "Network provider does not exist", e)

        }



        try {
            mLocationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MainActivity.INTERVAL,
                MainActivity.DISTANCE,
                MainActivity.locationListeners[0]
            )

        } catch (e: SecurityException) {

            Log.e(MainActivity.TAG, "Fail to request location update", e)

        } catch (e: IllegalArgumentException) {

            Log.e(MainActivity.TAG, "GPS provider does not exist", e)

        }
    }

    private fun stopLocationUpdates() {

        if (mLocationManager != null)

            for (i in 0..MainActivity.locationListeners.size) {
                try {
                    mLocationManager?.removeUpdates(MainActivity.locationListeners[i])
                } catch (e: Exception) {
                    Log.w(MainActivity.TAG, "Failed to remove location listeners")
                }
            }

    }


}
