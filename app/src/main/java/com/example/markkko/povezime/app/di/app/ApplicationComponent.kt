package com.example.markkko.povezime.app.di.app


import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.core.di.ActivityComponent
import com.example.markkko.povezime.core.di.ActivityModule
import com.example.markkko.povezime.core.di.RepositoryModule
import com.example.markkko.povezime.core.di.data.DataModule
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidModule::class), (ApplicationModule::class), (RepositoryModule::class)])
interface ApplicationComponent {

    fun activityBuilder(): ActivityComponent.Builder

    fun inject(app: PoveziMeApplication)

}
