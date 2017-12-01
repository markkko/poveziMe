package com.example.markkko.povezime.core.home.search


import com.example.markkko.povezime.app.di.GoogleApiModule

import dagger.Module
import dagger.Provides


@Module(includes = [(GoogleApiModule::class)])
class SearchModule {

    @Provides
    internal fun provideSearchPresenter(searchPresenter: SearchPresenter): ISearchMVP.Presenter =
            searchPresenter

    @Provides
    internal fun provideSearchInteractor(searchSearchInteractor: SearchInteractor): ISearchMVP.Interactor =
            searchSearchInteractor

}
