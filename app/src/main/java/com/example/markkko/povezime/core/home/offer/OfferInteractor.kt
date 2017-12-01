package com.example.markkko.povezime.core.home.offer

import android.graphics.Color
import android.util.Log
import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.util.RouteParser
import com.example.markkko.povezime.core.data.apis.OfferApi
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import com.example.markkko.povezime.core.models.Route
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import io.reactivex.Single
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject


class OfferInteractor @Inject constructor(private val offerApi: OfferApi,
                                          private val userRepository: UserRepository)
    : IOfferMVP.Interactor{

    override fun offerRide(offer: OfferRequest): Single<List<OfferResult>> =
            offerApi.offerRide(offer)

    override fun me(): User = userRepository.user

    fun getRoute(addresses: Array<LatLng?>): Single<String> {
        val url = getRouteUrl(addresses)
        return Single.fromCallable { downloadUrl(url) }
    }

    private fun getRouteUrl(addresses: Array<LatLng?>): String {

        // Origin of route
        val origin = "origin=" + addresses[0]!!.latitude + "," + addresses[0]!!.longitude

        // Destination of route
        val dest = "destination=" + addresses[1]!!.latitude + "," + addresses[1]!!.longitude

        var waypoints = "waypoints="

        (2..4).filter { addresses[it] != null }
                .forEach { waypoints += addresses[it]!!.latitude.toString() + "," + addresses[it]!!.longitude + "|" }

        // Sensor enabled
        val sensor = "sensor=false"

        // Building the parameters to the web service
        val parameters: String

        parameters = if (waypoints.length > 10)
            "$origin&$dest&$sensor&$waypoints"
        else
            "$origin&$dest&$sensor"

        // Output format
        val output = "json"

        // Building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters"
    }

    private @Throws(IOException::class) fun downloadUrl(strUrl: String): String {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)

            // Creating an http connection to communicate with url
            urlConnection = url.openConnection() as HttpURLConnection

            // Connecting to url
            urlConnection.connect()

            // Reading data from url
            iStream = urlConnection.inputStream

            val br = BufferedReader(InputStreamReader(iStream!!))

            val sb = StringBuffer()

            while (true) {
                val line = br.readLine()
                if (line == null) break
                else sb.append(line)
            }

            data = sb.toString()
            Log.d("downloadUrl", data)
            br.close()

        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            iStream!!.close()
            urlConnection!!.disconnect()
        }
        return data
    }

}