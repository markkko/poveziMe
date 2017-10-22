package com.example.markkko.povezime.core.home.search


import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.models.SearchResultData

interface SearchPresenter : BasePresenter {

    fun getSearchResults(data: SearchRequestData)

    var view: View?

    interface View : BaseView {
        fun showResults(results: List<SearchResultData>)
    }

}
