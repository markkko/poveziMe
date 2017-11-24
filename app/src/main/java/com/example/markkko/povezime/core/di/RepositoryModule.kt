package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.app.home.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository() = SearchRepository()

}
