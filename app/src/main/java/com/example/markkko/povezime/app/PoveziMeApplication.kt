package com.example.markkko.povezime.app

import android.app.Application
import android.content.Context
import com.example.markkko.povezime.app.di.app.AndroidModule
import com.example.markkko.povezime.app.di.app.ApplicationComponent
import com.example.markkko.povezime.app.di.app.DaggerApplicationComponent
import com.example.markkko.povezime.core.di.FeaturesModule


class PoveziMeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    private fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }

    fun activityComponent() =
            component.activityBuilder().featuresModule(FeaturesModule()).build()

    companion object {

        lateinit var component: ApplicationComponent
            private set

        operator fun get(context: Context): PoveziMeApplication =
                context.applicationContext as PoveziMeApplication
    }

}
