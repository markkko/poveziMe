package com.example.markkko.povezime.app.util

import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat


fun formatPoint(point: LatLng) : String {
    val df = DecimalFormat("#.###")

    val lat = df.format(point.latitude)
    val lng = df.format(point.longitude)

    return lat + "," + lng
}
