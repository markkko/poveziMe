package com.example.markkko.povezime.core.util

import io.reactivex.Scheduler


interface SchedulerProvider {

    fun mainThread(): Scheduler

    fun backgroundThread(): Scheduler

}
