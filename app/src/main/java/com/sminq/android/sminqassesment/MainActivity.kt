package com.sminq.android.sminqassesment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.sminq.android.sminqassesment.view.RestaurantListFragment


class MainActivity : AppCompatActivity() {

    companion object {

        val MY_PERMISSIONS_REQUEST_LOCATION = 101

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, RestaurantListFragment()).commit()
        }

        //checkLocationPermission()

        //fetchDataFromRepository()
    }



    private fun checkLocationPermission() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    showToast("MY_PERMISSIONS_REQUEST_LOCATION - If")

                } else {

                    showToast("MY_PERMISSIONS_REQUEST_LOCATION - else")
                }
                return
            }


        }
    }

    fun showToast(text: String) {

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
