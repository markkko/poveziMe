package com.example.markkko.povezime.app.util

import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat


fun formatPoint(point: LatLng) : String {
    val df = DecimalFormat("#.###")

    val lat = df.format(point.latitude)
    val lng = df.format(point.longitude)

    return lat + "," + lng
    //return "${point.latitude},${point.longitude}"
}

fun formatPoint(point: HashMap<String, String>) : String {
    val lat = point["lat"]?.toDouble()
    val lng = point["lng"]?.toDouble()
    if (lat != null && lng != null)
        return formatPoint(LatLng(lat, lng))
    return ""
}
