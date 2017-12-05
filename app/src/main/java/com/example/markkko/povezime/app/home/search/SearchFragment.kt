package com.example.markkko.povezime.app.home.search

import android.util.Log
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.app.util.formatPoint
import com.example.markkko.povezime.app.util.getIntSafe
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.models.SearchRequest
import com.example.markkko.povezime.core.models.SearchResult
import com.example.markkko.povezime.core.util.getTodayString
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.PlaceBuffer
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseHomeFragment(), ISearchMVP.View {

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun showResults(results: List<SearchResult>) {
        if (results.isNotEmpty()) showToast("Some results")
        else showToast("No results")
    }

    /**********************
     * Fields
     **********************/

    @Inject
    lateinit var presenter: ISearchMVP.Presenter

    /**********************
     * Setup
     **********************/

    override var layoutId: Int = R.layout.fragment_search

    override fun subscribeForUIEvents() {
        super.subscribeForUIEvents()

        val calendar = Calendar.getInstance()
        val yy = calendar.get(Calendar.YEAR)
        val mm = calendar.get(Calendar.MONTH)
        val dd = calendar.get(Calendar.DAY_OF_MONTH)

        val dateString = (yy.toString() + "-" + (mm + 1).toString() + "-"
                + dd.toString())

        RxView.clicks(search)
                .throttleFirst(2, TimeUnit.SECONDS)
               /* .filter {
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
                }*/
                .subscribe { valid -> presenter.getSearchResults(createSearch()) }
                //.subscribe { valid -> presenter.getSearchResults(SearchRequest.getDefaultRequest(presenter.me().id, getTodayString())) }
    }



    override fun injectDependencies(application: PoveziMeApplication) {
        application.activityComponent().inject(this)
    }

    override fun bind() {
        presenter.view = this
    }

    /**********************
     * Internal
     **********************/

    override fun onFromUpdateInternal(place: Place) {
        src = place.latLng
    }

    override fun onToUpdateInternal(place: Place) {
        dst = place.latLng
    }

    private fun createSearch(): SearchRequest {
        val search =  SearchRequest(presenter.me().id, from = formatPoint(src!!), to = formatPoint(dst!!), date = dateString!!, luggage = if (luggage.isChecked) 1 else 0,
                seats = getIntSafe(seats), oneDay = if (oneDay.isChecked) 1 else 0)
        Log.d("searchRequest", search.toString())
        return search
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}
