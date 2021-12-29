package com.jay.wjshop.utils

import android.util.Log
import com.jay.wjshop.BuildConfig

fun makeLog(simpleName: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(simpleName, msg)
}