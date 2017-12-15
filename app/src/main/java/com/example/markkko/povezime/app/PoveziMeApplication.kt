package com.example.markkko.povezime.app

import android.app.Application
import android.content.Context
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.di.app.AndroidModule
import com.example.markkko.povezime.app.di.app.ApplicationComponent
import com.example.markkko.povezime.app.di.app.ApplicationModule
import com.example.markkko.povezime.app.di.app.DaggerApplicationComponent
import com.example.markkko.povezime.core.di.ActivityModule


class PoveziMeApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .androidModule(AndroidModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }


    fun activityComponent(activity: BaseActivity) =
            appComponent.activityBuilder().activityModule(ActivityModule(activity)).build()

}
