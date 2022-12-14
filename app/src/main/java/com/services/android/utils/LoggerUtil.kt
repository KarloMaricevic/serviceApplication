package com.services.android.utils

import android.util.Log

class LoggerUtil {

    companion object {
        const val EMPTY_LOG_TAG = ""
    }

    fun logInfoMessage(msg: String) {
        Log.i(EMPTY_LOG_TAG, msg)
    }
}
