package com.jay.common

import android.util.Log

fun makeLog(simpleName: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(simpleName, msg)
}