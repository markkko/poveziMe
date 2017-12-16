package com.example.markkko.povezime.core.models



class MatchInfo(override val id: Long,
                val type: RideType,
                val answer: AnswerType,
                val search: Search,
                val offer: Offer): BaseEntity