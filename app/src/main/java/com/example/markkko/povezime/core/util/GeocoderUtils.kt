package com.example.markkko.povezime.core.util


import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import javax.inject.Inject

class GeocoderUtils @Inject constructor(private var geocoder: Geocoder) {


    fun getCityName(latLng: LatLng): String? {
        try {
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            return addresses[0].locality
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun getCityName(point: HashMap<String, String>): String? {
        val lat = point["lat"]?.toDouble()
        val lng = point["lng"]?.toDouble()
        if (lat != null && lng != null) {
            try {
                val addresses = geocoder.getFromLocation(lat, lng, 1)
                return addresses[0].locality
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }
}
