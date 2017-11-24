package com.example.markkko.povezime.core.models


import com.google.android.gms.maps.model.LatLng


data class SearchRequestData (var src: LatLng,
                         var dst: LatLng,
                         var date: String,
                         var luggage: Int,
                         var oneDay: Int,
                         var seats: Int,
                         //var user: UserDTO,
                         var offerId: String? = null)
