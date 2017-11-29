package com.example.markkko.povezime.core.base

import com.example.markkko.povezime.core.models.User


interface BaseUserPresenter: BasePresenter {

    fun me(): User

}