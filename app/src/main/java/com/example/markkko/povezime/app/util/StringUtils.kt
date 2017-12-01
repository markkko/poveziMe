package com.example.markkko.povezime.app.util

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

fun isNullOrEmpty(string: String?): Boolean {
    return string == null || "" == string
}

fun isNullOrEmpty(string: CharSequence?): Boolean {
    return string == null || "" == string
}

fun isNullOrEmpty(input: TextView?): Boolean {
    return input == null || input.text.toString() == ""
}

fun isNullOrEmpty(input: EditText?): Boolean {
    return input == null || input.text.toString() == ""
}

fun isNumberBad(input: EditText?): Boolean {
    if (input == null) {
        return true
    }

    try {
        val number = java.lang.Float.parseFloat(input.text.toString())
        return number < 0
    } catch (e: Exception) {
        return true
    }

}

fun getFloatSafe(input: EditText): Float {
    return try {
        java.lang.Float.parseFloat(input.text.toString())
    } catch (e: Exception) {
        0f
    }
}

fun getIntSafe(input: EditText): Int {
    return try {
        input.text.toString().toInt()
    } catch (e: Exception) {
        0
    }
}

fun getStringSafe(input: TextView?): String {
    return if (input == null || input.text == null) "" else getStringSafe(input.text.toString())
}

fun getStringSafe(input: EditText?): String {
    return if (input == null || input.text == null) "" else getStringSafe(input.text.toString())
}

fun getStringSafe(input: String): String {
    return if (isNullOrEmpty(input)) {
        ""
    } else input
}

fun getStringSafe(input: String, replacement: String): String {
    return if (isNullOrEmpty(input)) {
        replacement
    } else input
}

fun isValidEmail(target: EditText?): Boolean {
    return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target.text).matches()
}

fun isValidEmail(target: CharSequence?): Boolean {
    return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isValidPhoneNumber(phoneNumber: CharSequence): Boolean {
    return !TextUtils.isEmpty(phoneNumber) && android.util.Patterns.PHONE.matcher(phoneNumber).matches()
}

fun isValidWebsite(target: EditText): Boolean {
    return !isNullOrEmpty(target) && Patterns.WEB_URL.matcher(target.text).matches()
}

fun isValidWebsite(target: CharSequence): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.WEB_URL.matcher(target).matches()
}

fun passwordCharValidation(password: String): Boolean {
    if (password.length < 8) {
        return false
    }
    val PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$"
    val pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher = pattern.matcher(password)
    return !password.matches(".*\\d.*".toRegex()) || !matcher.matches()
}

fun passwordValidationShort(password: String): Boolean {
    return password.length > 8
}
