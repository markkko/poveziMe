package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.Car
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CarApi {

    @POST("car/new")
    fun addCar(@Body data: Car): Single<Car>

}