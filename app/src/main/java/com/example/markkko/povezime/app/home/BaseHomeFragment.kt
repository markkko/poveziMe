package com.example.markkko.povezime.app.home

import android.app.DatePickerDialog
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.core.home.PlaceAutocompleteAdapter
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.places.PlaceBuffer
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.model.LatLng
import java.util.*
import javax.inject.Inject


abstract class BaseHomeFragment : BaseFragment(), GoogleApiClient.OnConnectionFailedListener {

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var mGoogleApiClient: GoogleApiClient

    @Inject
    lateinit var mAutocompleteAdapter: PlaceAutocompleteAdapter

    @BindView(R.id.from_autocomplete_places)
    lateinit var fromAutocomplete: AutoCompleteTextView

    @BindView(R.id.to_autocomplete_places)
    lateinit var toAutocomplete: AutoCompleteTextView

    @BindView(R.id.dateButto)
    lateinit var dateButton: Button

    @BindView(R.id.dateLabel)
    lateinit var dateLabel: TextView

    var src: LatLng? = null
    var dst: LatLng? = null

    var dateString: String? = null

    /**********************
     * Callbacks
     **********************/

    private val mUpdatePlaceDetailsCallbackFrom = ResultCallback<PlaceBuffer> { places ->
        if (!places.status.isSuccess) {
            places.release()
            return@ResultCallback
        }
        onFromUpdateInternal(places)
    }
    private val mUpdatePlaceDetailsCallbackTo = ResultCallback<PlaceBuffer> { places ->
        if (!places.status.isSuccess) {
            places.release()
            return@ResultCallback
        }
        onToUpdateInternal(places)
    }

    private val mAutocompleteClickListenerFrom = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item = mAutocompleteAdapter.getItem(position)
        val placeId = item!!.placeId

        val placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallbackFrom)
    }
    private val mAutocompleteClickListenerTo = AdapterView.OnItemClickListener { _, view, position, id ->
        val item = mAutocompleteAdapter.getItem(position)
        val placeId = item!!.placeId

        val placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallbackTo)
    }

    private var dateListener: View.OnClickListener = View.OnClickListener {
        val calendar = Calendar.getInstance()
        val yy = calendar.get(Calendar.YEAR)
        val mm = calendar.get(Calendar.MONTH)
        val dd = calendar.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            dateString = (year.toString() + "-" + (monthOfYear + 1).toString() + "-"
                    + dayOfMonth.toString())
            dateLabel.text = (dayOfMonth.toString() + " / " + (monthOfYear + 1).toString() + " / "
                    + year.toString())
        }, yy, mm, dd)
        datePicker.show()
    }

    /**********************
     * Setup
     **********************/

    override fun prepareData() {
        mGoogleApiClient.connect()
        fromAutocomplete.setAdapter<PlaceAutocompleteAdapter>(mAutocompleteAdapter)
        toAutocomplete.setAdapter<PlaceAutocompleteAdapter>(mAutocompleteAdapter)
        dateLabel.text = ""
    }

    override fun subscribeForUIEvents() {
        fromAutocomplete.onItemClickListener = mAutocompleteClickListenerFrom
        toAutocomplete.onItemClickListener = mAutocompleteClickListenerTo
        dateButton.setOnClickListener(dateListener)
    }


    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }

    /**********************
     * Internal
     **********************/

    abstract fun onFromUpdateInternal(places: PlaceBuffer)

    abstract fun onToUpdateInternal(places: PlaceBuffer)
}
