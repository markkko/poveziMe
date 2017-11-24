package com.example.markkko.povezime.app.di.app


import com.example.markkko.povezime.core.di.ActivityComponent
import com.example.markkko.povezime.core.di.RepositoryModule
import com.example.markkko.povezime.core.di.data.DataModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApplicationModule::class, DataModule::class, RepositoryModule::class))
interface ApplicationComponent {

    fun activityBuilder(): ActivityComponent.Builder

}
