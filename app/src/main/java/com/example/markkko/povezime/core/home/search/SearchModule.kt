package com.example.markkko.povezime.core.home.search


import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.di.data.ClientModule
import com.example.markkko.povezime.core.di.data.RepositoryModule

import dagger.Module
import dagger.Provides


@Module(includes = arrayOf(GoogleApiModule::class))
class SearchModule {

    @Provides
    internal fun provideSearchPresenter(searchPresenterImp: SearchPresenterImp): SearchPresenter =
            searchPresenterImp

    @Provides
    internal fun provideSearchInteractor(searchInteractor: SearchInteractorImpl): SearchInteractor =
            searchInteractor

}
