package com.example.markkko.povezime.core.base

import android.content.pm.ServiceInfo
import io.reactivex.disposables.CompositeDisposable


interface BasePresenter{

    var disposables: CompositeDisposable

    fun dispose() {
        rxDispose()
    }

    fun clear()

}
