package com.example.markkko.povezime.app.home.offer

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import butterknife.OnClick
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.app.results.ResultsActivity
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.home.offer.IOfferMVP
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Route
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.util.convertTimeToString
import com.example.markkko.povezime.core.util.getTodayString
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.PlaceBufferResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnCompleteListener
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_offer.*
import kotlinx.android.synthetic.main.fragment_requests.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class OfferFragment : BaseHomeFragment(), OnMapReadyCallback, IOfferMVP.View {


    override fun showMessage(message: String) {
        baseActivity.showToast(message)
    }

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onOfferSuccess(results: List<Search>) {
        if (results.isNotEmpty()) {
            val bundle = Bundle()
            bundle.putInt(ResultsActivity.TYPE, 1)
            baseActivity.navigateToActivity(ResultsActivity::class.java, bundle)
        }
        else {
            AlertDialog.Builder(context)
                    .setTitle(getString(R.string.dialog_no_results_title))
                    .setMessage(getString(R.string.dialog_no_results_message))
                    .setNeutralButton("Ok") { _, _ -> }
                    .create().show()
        }
    }

    override fun onRouteFetched(route: Route) {
        val lineOptions = route.lineOptions
        mapView.addPolyline(lineOptions)
        fixZoom(lineOptions)
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) mapView = p0
        // Add a marker in Sydney and move the camer
        if (addresses[0] != null) {
            val options = MarkerOptions()
            addresses[0]?.let { options.position(it) }
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            mapView.moveCamera(CameraUpdateFactory.newLatLng(addresses[0]))
        }
    }

    /**********************
     * Fields
     ***********************/

    @Inject
    lateinit var presenter: IOfferMVP.Presenter

    private lateinit var mapView: GoogleMap

    private val addresses = arrayOfNulls<LatLng>(5)

    private lateinit var mapFragment: SupportMapFragment

    private var timeString: String? = null

    /**********************
     * Callbacks
     ***********************/

    private var timeListener: View.OnClickListener = View.OnClickListener {
        val calendar = Calendar.getInstance()
        val hh = calendar.get(Calendar.HOUR_OF_DAY)
        val mm = calendar.get(Calendar.MINUTE)

        val datePicker = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            timeString = convertTimeToString(hours, minutes)
            timeLabel.text = timeString

        }, hh, mm, true)
        datePicker.show()
    }

    @OnClick(R.id.acrossButton)
    fun onClickAcross() {
        acrossLayout.visibility = if (acrossLayout.visibility == View.GONE) View.VISIBLE else View.GONE
    }

    @OnClick(R.id.addCarButton)
    fun onClickAddCar() {
        val intent = Intent(activity, AddCarActivity::class.java)
        startActivity(intent)
    }

    /**********************
     * Setup
     ***********************/

    override var layoutId: Int = R.layout.fragment_offer

    override fun prepareData() {
        super.prepareData()

        timeLabel.text = ""

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        opt1.setAdapter(mAutocompleteAdapter)
        opt2.setAdapter(mAutocompleteAdapter)
        opt3.setAdapter(mAutocompleteAdapter)
    }

    override fun onResume() {
        super.onResume()

        val user = presenter.me()

        if (user.cars.isEmpty()) {
            addCarLayout.visibility = View.VISIBLE
            mainView.visibility = View.GONE
        } else {
            val spinnerValues = java.util.ArrayList<String>()
            user.cars.forEach { spinnerValues.add(it.make + " " + it.model) }

            val spinnerAdapter = ArrayAdapter(activity, R.layout.spinner_item_simple, spinnerValues)
            spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            carSpinner.adapter = spinnerAdapter

            mainView.visibility = View.VISIBLE
            addCarLayout.visibility = View.GONE
        }
    }

    override fun subscribeForUIEvents() {
        super.subscribeForUIEvents()
        opt1.onItemClickListener = mAutocompleteClickListenerOpt1
        opt2.onItemClickListener = mAutocompleteClickListenerOpt2
        opt3.onItemClickListener = mAutocompleteClickListenerOpt3

        timeButton.setOnClickListener(timeListener)

        RxView.clicks(offerButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .filter {
                    var valid = true

                    if (src == null) {
                        fromAutocomplete.error = getString(R.string.empty_from)
                        valid = false
                    }
                    if (dst == null) {
                        toAutocomplete.error = getString(R.string.empty_to)
                        valid = false
                    }
                    /* if (isNullOrEmpty(dateString)) {
                         dateLabel.error = getString(R.string.empty_date)
                         valid = false
                     }
                     if (isNullOrEmpty(timeString)) {
                         timeLabel.error = getString(R.string.empty_date)
                         valid = false
                     }*/
                    if (isNullOrEmpty(seats)) {
                        seats.error = getString(R.string.empty_seats)
                        valid = false
                    }
                    if (presenter.route == null) {
                        baseActivity.showToast("Molimo uzaberite polaznu i krajnju tacku")
                        valid = false
                    }
                    valid
                }
                .subscribe { valid ->
                    val offer = createOffer()
                    offer?.let { presenter.offerRide(it) }
                }
    }

    override fun bind() {
        presenter.view = this
    }

    override fun injectDependencies() {
        baseActivity.injector.inject(this)
    }

    /**********************
     * Internal
     ***********************/

    private fun fixZoom(route: PolylineOptions) {
        val points = route.points // route is instance of PolylineOptions
        val bc = LatLngBounds.Builder()
        for (item in points) {
            bc.include(item)
        }
        mapView.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50))
    }

    /**********************
     * Point callbacks
     ***********************/

    override fun onFromUpdateInternal(place: Place) {
        addresses[0] = place.latLng
        src = place.latLng

        mapView.clear()
        val options = MarkerOptions()
        options.position(addresses[0]!!)
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mapView.addMarker(options)
        mapView.moveCamera(CameraUpdateFactory.newLatLng(addresses[0]))
        addresses[1]?.let {
            val options2 = MarkerOptions()
            options2.position(it)
            options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }
    }

    override fun onToUpdateInternal(place: Place) {
        addresses[1] = place.latLng
        dst = place.latLng

        mapView.clear()
        val options = MarkerOptions()
        options.position(addresses[1]!!)
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        mapView.addMarker(options)
        addresses[0]?.let {
            val options2 = MarkerOptions()
            options2.position(it)
            options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }
    }

    private val mUpdatePlaceDetailsCallbackOpt1 = OnCompleteListener<PlaceBufferResponse> { task ->
        val places = task.result
        val place = places[0]

        addresses[2] = place.latLng

        opt2Input.visibility = View.VISIBLE

        if (addresses[0] != null && addresses[1] != null) {
            mapView.clear()
            val options = MarkerOptions()
            options.position(addresses[0]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            options.position(addresses[1]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }

        places.release()
    }

    private val mUpdatePlaceDetailsCallbackOpt2 = OnCompleteListener<PlaceBufferResponse> { task ->
        val places = task.result
        val place = places[0]

        addresses[3] = place.latLng
        opt3Input.visibility = View.VISIBLE

        if (addresses[0] != null && addresses[1] != null) {
            mapView.clear()
            val options = MarkerOptions()
            options.position(addresses[0]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            options.position(addresses[1]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }

        places.release()
    }

    private val mUpdatePlaceDetailsCallbackOpt3 = OnCompleteListener<PlaceBufferResponse> { task ->
        val places = task.result
        val place = places[0]

        addresses[4] = place.latLng

        if (addresses[0] != null && addresses[1] != null) {
            mapView.clear()
            val options = MarkerOptions()
            options.position(addresses[0]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            options.position(addresses[1]!!)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }

        places.release()
    }

    @OnClick(R.id.opt1, R.id.opt2, R.id.opt3)
    fun onClickOpt(view: View) {
        when (view.id) {
            R.id.opt1 -> {
                if (addresses[3] == null)
                    opt2Input.visibility = View.GONE
                if (addresses[4] == null)
                    opt3Input.visibility = View.GONE
                addresses[2] = null
            }
            R.id.opt2 -> {
                if (addresses[2] == null)
                    opt2Input.visibility = View.GONE
                if (addresses[4] == null)
                    opt3Input.visibility = View.GONE
                addresses[3] = null
            }
            R.id.opt3 -> {
                if (addresses[2] == null)
                    opt2Input.visibility = View.GONE
                if (addresses[3] == null)
                    opt3Input.visibility = View.GONE
                addresses[4] = null
            }
        }
    }

    private val mAutocompleteClickListenerOpt1 = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter.getItem(position)
        val placeId = item.placeId

        val placeResult = mGeoDataClient.getPlaceById(placeId)
        placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallbackOpt1)
    }

    private val mAutocompleteClickListenerOpt2 = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter.getItem(position)
        val placeId = item.placeId

        val placeResult = mGeoDataClient.getPlaceById(placeId)
        placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallbackOpt2)
    }

    private val mAutocompleteClickListenerOpt3 = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter.getItem(position)
        val placeId = item.placeId

        val placeResult = mGeoDataClient.getPlaceById(placeId)
        placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallbackOpt3)
    }

    private fun createOffer(): Offer? {
        val carId = presenter.me().cars[carSpinner.selectedItemPosition].id
        presenter.route?.let {
            val fromName = geocoder.getCityName(it.fullRoute.first())
            val toName = geocoder.getCityName(it.fullRoute.last())
            if (fromName != null && toName != null)
                return Offer(userId = presenter.me().id, date = getTodayString(), time = "13:25:00", route = presenter.route!!.toBackendString(),
                        seats = 3, luggage = 2, fromName = fromName, toName = toName, carId = carId)
        }
        return null
    }

    /**********************
     * Companion
     ***********************/

    companion object {
        fun newInstance(): OfferFragment = OfferFragment()
    }

}