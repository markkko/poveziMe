package com.example.markkko.povezime.core.profile

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single


interface IProfileMVP {

    interface View: BaseView {
        fun onUserUpdated(user: User)
    }

    interface Presenter: BaseUserPresenter {
        var view: View

        fun updateUser(data: User)
    }

    interface Interactor: BaseUserInteractor {
        fun updateUser(data: User): Single<User>
    }

}