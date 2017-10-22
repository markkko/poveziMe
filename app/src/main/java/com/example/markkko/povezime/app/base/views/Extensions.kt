package com.example.markkko.povezime.app.base.views

import android.content.Intent
import android.os.Bundle
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

fun BaseActivity.navigateToActivityForResult(activity: Class<*>, requestCode: Int) {
    val intent = Intent(this, activity)
    startActivityForResult(intent, requestCode)
}

fun BaseFragment.showToast(message: String) {
    (activity as BaseActivity).showToast(message)
}

fun BaseFragment.showToast(stringId: Int) {
    (activity as BaseActivity).showToast(stringId)
}

fun BaseFragment.navigateToActivityAndClearStackWithExtras(activity: Class<*>, bundle: Bundle) {
    val getActivity = getActivity() as BaseActivity
    val intent = Intent(getActivity, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun BaseFragment.navigateToActivityAndFinish(activity: Class<*>, bundle: Bundle) {
    val getActivity = getActivity() as BaseActivity
    val intent = Intent(getActivity, activity)
    intent.putExtras(bundle)
    startActivity(intent)
    getActivity.finish()
}

fun BaseFragment.navigateToActivity(activity: Class<*>, bundle: Bundle) {
    val getActivity = getActivity() as BaseActivity
    val intent = Intent(getActivity, activity)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun BaseFragment.navigateToActivityForResult(activity: Class<*>, requestCode: Int) {
    val getActivity = getActivity() as BaseActivity
    val intent = Intent(getActivity, activity)
    startActivityForResult(intent, requestCode)
}
