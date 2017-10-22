package com.example.markkko.povezime.core.models


data class UserData (var email: String,
                     var name: String,
                     var surname: String,
                     var phone: String,
                     var profilePicture: String? = null,
                     var viber: Int,
                     var whatsapp: Int,
                     var cars: List<CarData>? = null,
                     var selectedCar: CarData? = null)
