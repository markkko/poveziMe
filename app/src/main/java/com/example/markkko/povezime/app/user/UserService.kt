package com.example.markkko.povezime.app.user

import android.content.SharedPreferences
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import com.google.gson.Gson


object UserService {

    lateinit var user: User

    fun updateUser(prefs: SharedPreferences, user: User): Boolean {
        val json = Gson().toJson(user)
        if (json == "") return false
        val editor = prefs.edit()
        editor.putString(AppConstants.PREF_USER, json).apply()
        return true
    }

    fun updateUser(prefs: SharedPreferences, carData: Car): Boolean {
        val userJson = prefs.getString(AppConstants.PREF_USER, "")
        if (userJson == "") return false
        val gson = Gson()
        val user = gson.fromJson(userJson, User::class.java)
        user.cars.add(carData)
        updateUser(prefs, user)
        return true
    }

}