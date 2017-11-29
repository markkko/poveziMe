package com.example.markkko.povezime.app.home.offer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import butterknife.OnClick
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.core.home.offer.IOfferMVP
import com.example.markkko.povezime.core.models.OfferResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.places.PlaceBuffer
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_offer.*
import javax.inject.Inject


class OfferFragment : BaseHomeFragment(), OnMapReadyCallback, IOfferMVP.View {


    override fun showMessage(message: String) {

    }

    override fun showOfflineMessage(isCritical: Boolean) {

    }

    override fun onOfferSuccess(results: List<OfferResult>) {

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

    lateinit var mapView: GoogleMap

    private val addresses = arrayOfNulls<LatLng>(5)

    lateinit var mapFragment: SupportMapFragment

    /**********************
     * Callbacks
     ***********************/

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

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()

        val user = presenter.me()

        if (user.cars.isEmpty()) {
            addCarLayout.visibility = View.VISIBLE
            mainScrollView.visibility = View.GONE
        } else {
            mainScrollView.visibility = View.VISIBLE
            addCarLayout.visibility = View.GONE
        }
    }

    override fun subscribeForUIEvents() {
        super.subscribeForUIEvents()
        opt1.onItemClickListener = mAutocompleteClickListenerOpt1
        opt2.onItemClickListener = mAutocompleteClickListenerOpt2
        opt3.onItemClickListener = mAutocompleteClickListenerOpt3
    }

    override fun bind() {
        presenter.view = this
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.activityComponent().inject(this)
    }

    /**********************
     * Point callbacks
     ***********************/

    override fun onFromUpdateInternal(places: PlaceBuffer) {
        // Get the Place object from the buffer.
        val place = places.get(0)

        addresses[0] = place.latLng

        mapView.clear()
        val options = MarkerOptions()
        options.position(addresses[0]!!)
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mapView.addMarker(options)
        mapView.moveCamera(CameraUpdateFactory.newLatLng(addresses[0]))
        addresses[1]?.let {
            options.position(it)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }
        places.release()
    }

    override fun onToUpdateInternal(places: PlaceBuffer) {
        val place = places.get(0)
        addresses[1] = place.latLng
        mapView.clear()
        val options = MarkerOptions()
        options.position(addresses[1]!!)
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        mapView.addMarker(options)
        addresses[0]?.let {
            options.position(it)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mapView.addMarker(options)
            presenter.getRoute(addresses)
        }
        places.release()
    }

    private val mUpdatePlaceDetailsCallbackOpt1 = ResultCallback<PlaceBuffer> { places ->
        if (!places.status.isSuccess) {
            places.release()
            return@ResultCallback
        }
        // Get the Place object from the buffer.
        val place = places.get(0)

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

    private val mUpdatePlaceDetailsCallbackOpt2 = ResultCallback<PlaceBuffer> { places ->
        if (!places.status.isSuccess) {

            places.release()
            return@ResultCallback
        }
        // Get the Place object from the buffer.
        val place = places.get(0)

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

    private val mUpdatePlaceDetailsCallbackOpt3 = ResultCallback<PlaceBuffer> { places ->
        if (!places.status.isSuccess) {
            places.release()
            return@ResultCallback
        }
        // Get the Place object from the buffer.
        val place = places.get(0)

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
        val item = mAutocompleteAdapter!!.getItem(position)
        val placeId = item!!.placeId

        val placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallbackOpt1)
    }

    private val mAutocompleteClickListenerOpt2 = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter!!.getItem(position)
        val placeId = item!!.placeId

        val placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallbackOpt2)
    }

    private val mAutocompleteClickListenerOpt3 = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter!!.getItem(position)
        val placeId = item!!.placeId

        val placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallbackOpt3)
    }

    /**********************
     * Companion
     ***********************/

    companion object {
        fun newInstance(): OfferFragment = OfferFragment()
    }

}