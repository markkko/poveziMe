package com.example.markkko.povezime.app.user

import android.content.SharedPreferences
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.models.dto.CarDTO
import com.example.markkko.povezime.core.models.dto.UserDTO
import com.google.gson.Gson


object UserService {

    fun updateUser(prefs: SharedPreferences, userDTO: UserDTO): Boolean {
        val json = Gson().toJson(userDTO)
        if (json == "") return false
        val editor = prefs.edit()
        editor.putString(AppConstants.SHARED_PREF_USER, json).apply()
        return true
    }

    fun updateUser(prefs: SharedPreferences, carData: CarDTO): Boolean {
        val userJson = prefs.getString(AppConstants.SHARED_PREF_USER, "")
        if (userJson == "") return false
        val gson = Gson()
        val user = gson.fromJson(userJson, UserDTO::class.java)
        user.cars.add(carData)
        updateUser(prefs, user)
        return true
    }

}