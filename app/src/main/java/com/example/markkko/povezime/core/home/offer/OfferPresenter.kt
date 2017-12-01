package com.example.markkko.povezime.core.home.offer

import android.graphics.Color
import android.util.Log
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.Route
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.RouteParser
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject


class OfferPresenter @Inject constructor(private val offerInteractor: OfferInteractor,
                                         private val schedulerProvider: SchedulerProvider)
    : IOfferMVP.Presenter{


    override lateinit var view: IOfferMVP.View

    override var route: Route? = null

    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun getRoute(addresses: Array<LatLng?>) {
        offerInteractor.getRoute(addresses)
                .subscribeOn(schedulerProvider.backgroundThread())
                .doOnSuccess { route = parseRoute(it) }
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onRouteFetched(route!!)}, {view.showMessage("Route cannot be fetched")})
    }

    override fun offerRide(offer: OfferRequest) {
        rxTransaction {
            offerInteractor.offerRide(offer)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({view?.onOfferSuccess(it)}, {view?.showMessage(it.message!!)})
        }
    }

    private fun parseRoute(route: String) : Route? {
        val jObject: JSONObject
        var routes: List<List<HashMap<String, String>>>

        try {
            jObject = JSONObject(route)
            Log.d("ParserTask", route)
            val parser = RouteParser()
            Log.d("ParserTask", parser.toString())

            // Starts parsing data
            routes = parser.parse(jObject)
            Log.d("ParserTask", "Executing routes")
            Log.d("ParserTask", routes.toString())
            //getSteps();

            var points: ArrayList<LatLng>
            val lineOptions = PolylineOptions()

            // Traversing through all the routes
            for (i in routes.indices) {
                points = ArrayList()

                // Fetching i-th route
                val path = routes[i]

                // Fetching all the points in i-th route
                for (j in path.indices) {
                    val point = path[j]

                    val lat = java.lang.Double.parseDouble(point["lat"])
                    val lng = java.lang.Double.parseDouble(point["lng"])
                    val position = LatLng(lat, lng)
                    points.add(position)
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points)
                lineOptions.width(10f)
                lineOptions.color(Color.RED)
                Log.d("onPostExecute", "onPostExecute lineoptions decoded")
            }

            val route = Route(routes[0], lineOptions)
            return route

        } catch (e: Exception) {
            Log.d("ParserTask", e.toString())
            e.printStackTrace()
        }

        return null
    }

    override fun me(): User = offerInteractor.me()

    override fun clear() {

    }
}