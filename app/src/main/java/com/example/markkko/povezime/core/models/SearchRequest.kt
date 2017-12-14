package com.example.markkko.povezime.core.models


class SearchRequest(val id: Long,
                       val type: RequestType,
                       val answer: AnswerType,
                       val offer: Offer,
                       val search: Search)