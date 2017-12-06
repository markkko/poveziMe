package com.example.markkko.povezime.app.results.search

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.core.models.SearchRequestRes
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.results.search.ISearchResultsMVP
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject


class SearchResultsFragment: BaseFragment(), ISearchResultsMVP.View {

    override fun showMessage(message: String) {}

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onRequestPosted(request: SearchRequestRes) {

    }

    /*********** Fields **************/

    override val layoutId: Int = R.layout.fragment_result

    @Inject
    lateinit var adapter: SearchResultsAdapter

    @Inject
    lateinit var presenter: ISearchResultsMVP.Presenter

    /*********** Callbacks **************/

    private val onResultClickListener = object : RecyclerItemAdapter.ItemClickListener {
        override fun onItemClick(parent: View?, v: View, position: Int, entity: Any) {
            val result = entity as Search
            when (v.id) {

                //R.id.request -> if (!result.isSent) presenter.postRequest()
            }
        }
    }

    /*********** Setup **************/

    override fun prepareData() {
        activity.title = getString(R.string.title_fragment_search_results)

        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.adapter = adapter
        adapter.items = presenter.getResults()
    }

    override fun subscribeForUIEvents() {
        adapter.listener = onResultClickListener
    }

    override fun bind() {
        presenter.view = this
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.activityComponent().inject(this)
    }

    /*********** Internal **************/

    /*********** Companion **************/

    companion object {
        fun newInstance() = SearchResultsFragment()
    }
}