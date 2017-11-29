package com.example.markkko.povezime.app.home.search

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.home.search.SearchPresenter
import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.models.SearchResultData
import com.google.android.gms.location.places.PlaceBuffer
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseHomeFragment(), SearchPresenter.View {

    override fun showMessage(message: String) {
    }

    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun showResults(results: List<SearchResultData>) {

    }

    /**********************
     * Fields
     **********************/

    @Inject
    lateinit var searchPresenter: SearchPresenter

    /**********************
     * Setup
     **********************/

    override var layoutId: Int = R.layout.fragment_search

    override fun subscribeForUIEvents() {
        super.subscribeForUIEvents()
        RxView.clicks(search)
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
                    if (isNullOrEmpty(dateString)) {
                        dateLabel.error = getString(R.string.empty_date)
                        valid = false
                    }
                    if (isNullOrEmpty(seats)) {
                        seats.error = getString(R.string.empty_seats)
                        valid = false
                    }
                    valid
                }
                .subscribe { valid -> searchPresenter.getSearchResults(createSearch()) }
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.activityComponent().inject(this)
    }

    override fun bind() {
        searchPresenter.view = this
    }

    /**********************
     * Internal
     **********************/

    override fun onFromUpdateInternal(places: PlaceBuffer) {
        val place = places.get(0)
        src = place.latLng
        places.release()
    }

    override fun onToUpdateInternal(places: PlaceBuffer) {
        val place = places.get(0)
        dst = place.latLng
        places.release()
    }

    private fun createSearch(): SearchRequestData {
        return SearchRequestData(src = src!!, dst = dst!!, date = dateString!!, luggage = if (luggage.isChecked) 1 else 0,
                seats = seats.text.toString().toInt(), oneDay = if (oneDay.isChecked) 1 else 0)
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}
