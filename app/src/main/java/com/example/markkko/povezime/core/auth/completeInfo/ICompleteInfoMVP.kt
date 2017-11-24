package com.example.markkko.povezime.core.auth.completeInfo

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.dto.UserDTO
import io.reactivex.Single


interface ICompleteInfoMVP {

    interface View : BaseView {
        fun onInfoCompleted(user: UserDTO)
    }

    interface Presenter : BasePresenter {

        var view: View

        fun completeInfo(user: UserDTO)
    }


    interface Interactor : BaseInteractor {
        fun updateUser(user: UserDTO): Single<UserDTO>
    }
}
