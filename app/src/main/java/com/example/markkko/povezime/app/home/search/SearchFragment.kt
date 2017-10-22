package com.example.markkko.povezime.app.home.search

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.app.util.StringUtils
import com.example.markkko.povezime.core.home.search.SearchPresenter
import com.example.markkko.povezime.core.models.SearchRequestData
import com.jakewharton.rxbinding2.view.RxView

import java.util.concurrent.TimeUnit

import javax.inject.Inject

import butterknife.BindView
import com.example.markkko.povezime.app.getSearchSubComponent
import com.example.markkko.povezime.core.models.SearchResultData


class SearchFragment : BaseHomeFragment(), SearchPresenter.View {

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showOfflineMessage(isCritical: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResults(results: List<SearchResultData>) {

    }
    
    /**********************
     * Fields
     **********************/

    @Inject
    lateinit var searchPresenter: SearchPresenter

    @BindView(R.id.search_button)
    lateinit var searchButton: View

    @BindView(R.id.seats_label)
    lateinit var seats: EditText

    @BindView(R.id.luggageSwitch)
    lateinit var luggage: Switch

    @BindView(R.id.oneDayCheckBox)
    lateinit var oneDay: CheckBox

    /**********************
     * Setup
     **********************/

    override var layoutId: Int = R.layout.fragment_search

    override fun subscribeForUIEvents() {
        super.subscribeForUIEvents()
        RxView.clicks(searchButton)
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
                    if (StringUtils.isNullOrEmpty(dateString)) {
                        dateLabel.error = getString(R.string.empty_date)
                        valid = false
                    }
                    if (StringUtils.isNullOrEmpty(seats)) {
                        seats.error = getString(R.string.empty_seats)
                        valid = false
                    }
                    valid
                }
                .subscribe { valid -> searchPresenter!!.getSearchResults(createSearch()) }
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.getSearchSubComponent().inject(this)
    }

    override fun bind() {
        searchPresenter.view = this
    }

    /**********************
     * Internal
     **********************/

    private fun createSearch(): SearchRequestData {
        return SearchRequestData(src = src!!, dst = dst!!, date = dateString!!, luggage = if (luggage.isChecked) 1 else 0,
                seats = seats.text.toString().toInt(), oneDay = if (oneDay.isChecked) 1 else 0)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
