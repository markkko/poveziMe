package com.example.markkko.povezime.core.login


import android.app.Activity
import android.util.Log

import com.example.markkko.povezime.core.util.RXUtil
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.auth.FirebaseAuth

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

class LoginPresenterImpl @Inject
constructor(private val schedulerProvider: SchedulerProvider, private val loginInteractor: LoginInteractor) : LoginPresenter {

    private val mAuth: FirebaseAuth

    private var view: LoginPresenter.View? = null
    private var disposables: CompositeDisposable? = null

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun loginWithFirebase(context: Activity, firebaseAuth: FirebaseAuth, email: String, password: String) {
        sendInfoToServer("", "")
        /*
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						// If sign in fails, display a message to the user. If sign in succeeds
						// the auth state listener will be notified and logic to handle the
						// signed in user can be handled in the listener.
						if (!task.isSuccessful()) {
							view.onLoginFail();
						} else {
							view.onLoginSuccess(task.getResult().getUser());
						}
					}
				});
				*/
    }

    override fun sendInfoToServer(email: String, regId: String) {
        val data = LoginPostData()
        data.email = "marko.m@gmail.com"
        data.regId = "1234567890"

        if (disposables == null || disposables!!.isDisposed) {
            disposables = CompositeDisposable()
        }

        val disposable = loginInteractor.sendLoginInfoToServer(data)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({ view!!.onSendInfoSuccess(it) }) { throwable -> Log.d("www", throwable.toString()) }
        disposables!!.add(disposable)
    }

    override fun bind(view: LoginPresenter.View) {
        this.view = view
    }

    override fun unbind() {
        RXUtil.dispose(disposables)
    }
}
