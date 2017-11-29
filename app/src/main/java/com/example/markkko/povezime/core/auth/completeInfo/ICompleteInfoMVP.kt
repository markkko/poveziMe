package com.example.markkko.povezime.core.auth.completeInfo

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single


interface ICompleteInfoMVP {

    interface View : BaseView {
        fun onInfoCompleted(user: User)
    }

    interface Presenter : BaseUserPresenter {

        var view: View

        fun completeInfo(user: User)
    }


    interface Interactor : BaseUserInteractor {
        fun updateUser(user: User): Single<User>
    }
}
