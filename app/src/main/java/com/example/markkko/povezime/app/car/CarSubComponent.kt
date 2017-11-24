package com.example.markkko.povezime.app.car

import com.example.markkko.povezime.app.di.activity.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface CarSubComponent {

    fun inject(activity: AddCarActivity)

}
