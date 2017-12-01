package com.example.markkko.povezime.app.util

import com.example.markkko.povezime.core.util.SchedulerProvider

import javax.inject.Inject

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun backgroundThread(): Scheduler {
        return Schedulers.io()
    }

}
