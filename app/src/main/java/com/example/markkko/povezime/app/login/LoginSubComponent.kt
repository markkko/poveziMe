package com.example.markkko.povezime.app.login

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.login.LoginModule

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginSubComponent {

    fun inject(activity: LoginActivity)

}
