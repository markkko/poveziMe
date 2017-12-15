package com.example.markkko.povezime.core.di

import android.content.Context
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.app.util.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named


@ActivityScope
@Module(includes = [(PresenterModule::class), (GoogleApiModule::class)])
class ActivityModule(val activity: BaseActivity) {


    @Provides
    @Named(AppConstants.ACTIVITY_CONTEXT)
    internal fun provideActivityContext(): Context =
        activity


}