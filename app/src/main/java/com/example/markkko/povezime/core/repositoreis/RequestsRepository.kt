package com.example.markkko.povezime.core.repositoreis

import com.example.markkko.povezime.core.models.MatchInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestsRepository @Inject constructor() {

    var requests: List<MatchInfo>? = null

}
