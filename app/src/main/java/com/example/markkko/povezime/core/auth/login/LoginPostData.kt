package com.example.markkko.povezime.core.auth.login


import com.google.gson.annotations.SerializedName

class LoginPostData {

    var email: String? = null
        set(email) {
            field = this.email
        }

    @SerializedName("reg_id")
    var regId: String? = null
        set(regId) {
            field = this.regId
        }

}
