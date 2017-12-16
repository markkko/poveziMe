package com.example.markkko.povezime.core.requests

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.MatchInfo
import io.reactivex.Single


interface IRequestsMVP {

    interface View: BaseView {
        fun onRequestsFetched(requests: List<MatchInfo>)
    }

    interface Presenter: BasePresenter {
        var view: View
        fun getAllRequests()
    }

    interface Interactor: BaseInteractor {
        fun getAllRequests(): Single<List<MatchInfo>>
    }

}