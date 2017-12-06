package com.example.markkko.povezime.app.base.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast


fun BaseActivity.showToast(stringId: Int) {
    Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
}

fun BaseActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun BaseActivity.navigateToActivityAndClearStackWithExtras(activity: Class<*>, bundle: Bundle) {
    val intent = Intent(this, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun BaseActivity.navigateToActivityAndFinish(activity: Class<*>, bundle: Bundle) {
    val intent = Intent(this, activity)
    intent.putExtras(bundle)
    startActivity(intent)
    finish()
}

fun BaseActivity.navigateToActivity(activity: Class<*>, bundle: Bundle) {
    val intent = Intent(this, activity)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun BaseActivity.navigateToActivity(activity: Class<*>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun BaseActivity.navigateToActivityForResult(activity: Class<*>, requestCode: Int) {
    val intent = Intent(this, activity)
    startActivityForResult(intent, requestCode)
}

fun BaseActivity.showNeutralDialog(title: String, message: String, buttonText: String) {
    AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(buttonText) { _, _ -> }
            .create().show()
}
