package com.jay.local.prefs

import android.content.Context
import androidx.core.content.edit
import com.jay.local.utils.PREFS_APP_NAME
import com.jay.local.utils.PREFS_SHOP_NAME
import javax.inject.Inject

class PrefsHelperImpl @Inject constructor(applicationContext: Context): PrefsHelper {

    private val prefs = applicationContext.getSharedPreferences(PREFS_APP_NAME, Context.MODE_PRIVATE)

    override var shopName: String
        get() = prefs.getString(PREFS_SHOP_NAME, "") ?: ""
        @Synchronized
        set(value) {
            prefs.edit {
                putString(PREFS_SHOP_NAME, value)
            }
        }

}