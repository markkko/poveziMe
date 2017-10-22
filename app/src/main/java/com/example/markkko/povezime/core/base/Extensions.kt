package com.example.markkko.povezime.core.base

import io.reactivex.disposables.Disposable

fun BasePresenter.initDisposables() {
    if(disposables.isDisposed){
        disposables = io.reactivex.disposables.CompositeDisposable()
    }
}

fun BasePresenter.rxDispose(){
    if(!disposables.isDisposed){
        disposables.dispose()
    }
}

inline fun BasePresenter.rxTransaction(subscription: () -> Disposable){
    initDisposables()
    disposables.add(subscription())
}