package com.example.markkko.povezime.core.util;

import io.reactivex.Scheduler;


public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
