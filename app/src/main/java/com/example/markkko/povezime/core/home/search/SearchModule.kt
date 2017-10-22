package com.example.markkko.povezime.core.home.search


import com.example.markkko.povezime.app.di.activity.ActivityScope

import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @ActivityScope
    internal fun provideSearchPresenter(searchPresenterImp: SearchPresenterImp): SearchPresenter {
        return searchPresenterImp
    }

    @Provides
    @ActivityScope
    internal fun provideSearchInteractor(searchInteractor: SearchInteractorImpl): SearchInteractor {
        return searchInteractor
    }

}
