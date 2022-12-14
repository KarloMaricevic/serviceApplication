package com.services.android.utils.services

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.services.android.utils.CustomBroadcastReceiver
import com.services.android.utils.LoggerUtil
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class BasicService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {

        private const val MESSAGE_KEY = "MESSAGE"
        private const val RECEIVER_KEY = "RECEIVER"
        private const val EMPTY_MESSAGE = "No message"

        fun startServiceWithResultBackWithBroadcast(context: Context, message: String) {
            val intent = createIntentForService(context)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                200,
                Intent(context, CustomBroadcastReceiver::class.java),
                FLAG_ONE_SHOT
            )
            intent.putExtra(RECEIVER_KEY, pendingIntent)
            context.startService(intent)
        }

        fun startService(context: Context, message: String) {
            val intent = createIntentForService(context)
            intent.putExtra(MESSAGE_KEY, message)
            context.startService(intent)
        }

        fun boundService(
            context: Context,
            message: String,
            serviceConnection: ServiceConnection,
        ): IBinder? {
            PendingIntent.getActivity()
            val intent = createIntentForService(context)
            intent.putExtra(MESSAGE_KEY, message)
            context.bindService(intent, serviceConnection, BIND_ADJUST_WITH_ACTIVITY)
            return null
        }

        private fun createIntentForService(context: Context) =
            Intent(context, BasicService::class.java)
    }

    private val loggerUtil: LoggerUtil by inject()

    override fun onCreate() {
        loggerUtil.logInfoMessage("onCrete")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        loggerUtil.logInfoMessage("onStartCommand")
        sendMessage(extractMessageFromIntent(intent))
        val broadcast = intent.getParcelableExtra<PendingIntent>(RECEIVER_KEY)
        broadcast?.send(this, 200, Intent())
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        loggerUtil.logInfoMessage("onBind")
        sendMessage(extractMessageFromIntent(intent))
        return null
    }

    override fun onDestroy() {
        loggerUtil.logInfoMessage("onDestroy")
    }

    private fun extractMessageFromIntent(intent: Intent) =
        intent.getStringExtra(MESSAGE_KEY) ?: EMPTY_MESSAGE

    private fun sendMessage(message: String) {
        coroutineScope.launch {
            Log.i("Message", message)
        }
    }
}
