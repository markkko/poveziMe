package com.example.markkko.povezime.core.models

import com.example.markkko.povezime.app.util.formatPoint
import com.google.android.gms.maps.model.PolylineOptions
import java.util.HashMap
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.filter
import kotlin.collections.forEach
import kotlin.collections.mapTo


class Route(val fullRoute: List<HashMap<String, String>>,
            val lineOptions: PolylineOptions) {

    override fun toString(): String =
            getString(fullRoute)

    fun toBackendString(): String {
        val shortRoute = ArrayList<HashMap<String, String>>()
        shortRoute.add(fullRoute[0])
        (1..fullRoute.size)
                .filter { it % 34 == 0 || it == fullRoute.size - 1 }
                .mapTo(shortRoute) { fullRoute[it] }
        return getString(shortRoute)
    }

    private fun getString(route: List<HashMap<String, String>>): String {
        val str = StringBuilder()
        route.forEach {
            str.append(formatPoint(it))
            str.append(" - ")
        }

        return str.substring(0, str.length - 3)
    }

}