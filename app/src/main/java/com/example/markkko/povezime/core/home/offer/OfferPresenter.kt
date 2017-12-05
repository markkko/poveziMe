package com.example.markkko.povezime.core.home.offer

import android.graphics.Color
import android.util.Log
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.Route
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.RouteParser
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import java.util.*
import javax.inject.Inject


class OfferPresenter @Inject constructor(private val offerInteractor: IOfferMVP.Interactor,
                                         private val schedulerProvider: SchedulerProvider)
    : IOfferMVP.Presenter {


    override lateinit var view: IOfferMVP.View

    override var route: Route? = null

    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun getRoute(addresses: Array<LatLng?>) {
        offerInteractor.getRoute(addresses)
                .subscribeOn(schedulerProvider.backgroundThread())
                .doOnSuccess { route = parseRoute(it) }
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        {
                            if (route != null) view.onRouteFetched(route!!)
                            else view.showMessage("Route cannot be fetched")
                        },
                        { view.showMessage("Route cannot be fetched") })
    }

    override fun offerRide(offer: OfferRequest) {
        rxTransaction {
            offerInteractor.offerRide(offer)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({ view.onOfferSuccess(it) }, { view.showMessage(it.message!!) })
        }
    }

    private fun parseRoute(route: String): Route? {
        try {
            val jObject = JSONObject(route)
            val parser = RouteParser()

            // Starts parsing data
            val routes = parser.parse(jObject)

            routes?.let {
                //getSteps();
                var points: ArrayList<LatLng>
                val lineOptions = PolylineOptions()

                // Traversing through all the routes
                for (i in it.indices) {
                    points = ArrayList()

                    // Fetching i-th route
                    val path = it[i]

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

                return Route(routes[0], lineOptions)
            }
        } catch (e: Exception) {
            Log.d("ParserTask", e.toString())
            e.printStackTrace()
        }

        return null
    }

    override fun me(): User = offerInteractor.me()

    override fun getCars(): List<Car> = me().cars

    override fun clear() {

    }
}