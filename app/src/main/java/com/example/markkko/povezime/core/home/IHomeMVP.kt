package com.example.markkko.povezime.core.home

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView


interface IHomeMVP {

    interface View: BaseView {
        fun onLoggedOut()
    }

    interface Presenter: BasePresenter {
        var view: View

        fun logout()
    }

    interface Interactor: BaseInteractor {
        fun logout(): Boolean
    }

}