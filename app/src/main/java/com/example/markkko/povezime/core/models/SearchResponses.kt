package com.example.markkko.povezime.core.models



class SearchResultRes(val search: Search,
                      val offers: List<Offer>)

class SearchRequestRes(val id: Long,
                       val type: RequestType,
                       val answer: AnswerType,
                       val offer: Offer,
                       val search: Search)