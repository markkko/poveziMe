package com.example.markkko.povezime.core.models


class OfferResultRes(val offer: Offer,
                     val searches: List<Search>)

class OfferRequestRes(val id: Long,
                       val type: RequestType,
                       val answer: AnswerType)
