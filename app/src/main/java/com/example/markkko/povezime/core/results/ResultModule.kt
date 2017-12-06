package com.example.markkko.povezime.core.results

import com.example.markkko.povezime.core.results.search.ISearchResultsMVP
import com.example.markkko.povezime.core.results.search.SearchResultInteractor
import com.example.markkko.povezime.core.results.search.SearchResultPresenter
import dagger.Module
import dagger.Provides


@Module
class ResultModule {

    @Provides
    fun provideSearchResultInteractor(interactor: SearchResultInteractor): ISearchResultsMVP.Interactor =
            interactor

    @Provides
    fun provideSearchResultPresenter(presenter: SearchResultPresenter): ISearchResultsMVP.Presenter =
            presenter

}