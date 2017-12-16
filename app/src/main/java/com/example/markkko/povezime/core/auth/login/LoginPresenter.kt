package com.example.markkko.povezime.core.auth.login


import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.base.rxTransaction

import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.auth.FirebaseAuth

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

class LoginPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                         private val loginInteractor: ILoginMVP.Interactor,
                                         private val prefs: SharedPreferences)
    : ILoginMVP.Presenter{


    override lateinit var view: ILoginMVP.View
    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun loginWithFirebase(activity: Activity, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (!task.isSuccessful) {
                        Log.d("thr_login", task.exception!!.message)
                        view.onLoginFail()
                    } else {
                        sendInfoToServer(email,
                                prefs.getString(AppConstants.PREF_REG_ID, ""))
                    }
                }
    }

    override fun sendInfoToServer(email: String, regId: String) {
        val data = LoginPostData(email, regId)

        rxTransaction {
            loginInteractor.sendLoginInfoToServer(data)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .doOnSuccess {
                        Log.d("loginP", it.email)
                        prefs.edit().putString(AppConstants.PREF_EMAIL, it.email).apply()
                    }
                    .subscribe({ view.onSendInfoSuccess(it) })
                    { throwable -> Log.d("thr_send_info", throwable.toString()) }
        }
    }
}
