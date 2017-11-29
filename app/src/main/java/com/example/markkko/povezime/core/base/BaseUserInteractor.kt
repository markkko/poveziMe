package com.example.markkko.povezime.core.base

import com.example.markkko.povezime.core.models.User


interface BaseUserInteractor: BaseInteractor {

    fun me(): User

}