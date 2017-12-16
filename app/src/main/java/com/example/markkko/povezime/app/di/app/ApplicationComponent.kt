package com.example.markkko.povezime.app.di.app


import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.core.di.ActivityComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidModule::class), (ApplicationModule::class)])
interface ApplicationComponent {

    fun activityBuilder(): ActivityComponent.Builder

    fun inject(app: PoveziMeApplication)

}
