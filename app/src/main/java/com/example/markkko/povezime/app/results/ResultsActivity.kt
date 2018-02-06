package com.example.markkko.povezime.app.results

import android.support.v4.app.Fragment
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity
import com.example.markkko.povezime.app.results.offer.OfferResultsFragment
import com.example.markkko.povezime.app.results.search.SearchResultsFragment

class ResultsActivity : BaseFragmentedActivity() {

    companion object {
        const val TYPE = "type" // 0 = search; 1 = offer
    }

    override fun getFragment(): Fragment {
        val type = intent.getIntExtra(TYPE, 0)
        when (type) {
            0 -> return SearchResultsFragment.newInstance()
            1 -> return OfferResultsFragment.newInstance()
        }
        return SearchResultsFragment.newInstance()
    }
}
