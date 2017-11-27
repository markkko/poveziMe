package com.example.markkko.povezime.core.auth.login


import com.google.gson.annotations.SerializedName

data class LoginPostData(val email: String,
                         @SerializedName("reg_id") val regId: String)
