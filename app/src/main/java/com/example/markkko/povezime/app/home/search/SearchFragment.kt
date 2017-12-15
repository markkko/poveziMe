package com.example.markkko.povezime.app.home.search

import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.app.results.ResultsActivity
import com.example.markkko.povezime.app.util.formatPoint
import com.example.markkko.povezime.app.util.getIntSafe
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.SearchResultsReq
import com.google.android.gms.location.places.Place
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseHomeFragment(), ISearchMVP.View {

    override fun showMessage(message: String) {
        baseActivity.showToast(message)
    }

    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun showResults(results: List<Offer>) {
        if (results.isNotEmpty()) baseActivity.navigateToActivity(ResultsActivity::class.java)
        else {
            AlertDialog.Builder(context)
                    .setTitle("Nema rezultata!")
                    .setMessage("Bicete obavesteni ako se pojavi")
                    .setNeutralButton("Ok") { _, _ -> }
                    .create().show()
        }
    }

    /*********** Fields **************/

    @Inject
    lateinit var presenter: ISearchMVP.Presenter

    /*********** Setup **************/

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
                .subscribe { valid ->
                    val searchRequest = createSearch()
                    searchRequest?.let { presenter.getSearchResults(it) }
                }
        //.subscribe { valid -> presenter.getSearchResults(SearchResultsReq.getDefaultRequest(presenter.me().id, getTodayString())) }
    }

    override fun injectDependencies() {
        baseActivity.injector.inject(this)
    }

    override fun bind() {
        presenter.view = this
    }

    /*********** Internal **************/

    override fun onFromUpdateInternal(place: Place) {
        src = place.latLng
    }

    override fun onToUpdateInternal(place: Place) {
        dst = place.latLng
    }

    private fun createSearch(): SearchResultsReq? {
        val fromName = geocoder.getCityName(src!!)
        val toName = geocoder.getCityName(dst!!)
        if (fromName != null && toName != null) {
            val search = SearchResultsReq(presenter.me().id, from = formatPoint(src!!), to = formatPoint(dst!!),
                    date = dateString!!, luggage = if (luggage.isChecked) 1 else 0,
                    seats = getIntSafe(seats), oneDay = if (oneDay.isChecked) 1 else 0,
                    fromName = fromName, toName = toName)
            Log.d("searchRequest", search.toString())
            return search
        }
        return null
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}
