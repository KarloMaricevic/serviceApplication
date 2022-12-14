package com.services.android

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.services.android.utils.CustomBroadcastReceiver
import com.services.android.utils.services.BasicService

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity","OnCreate")
        CustomBroadcastReceiver()
        ContextCompat.registerReceiver(
            this,
            CustomBroadcastReceiver(),
            IntentFilter(),
            ContextCompat.RECEIVER_EXPORTED
        )
        //openServicePickerScreen()
        Intent().component =
    }

    private fun openServicePickerScreen() {
        setContent {
            //TODO
        }
    }

    private fun startBasicService() {
        BasicService.startService(this, "Message")
        /*BasicService.boundService(this,"message", object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                var i = 5
                i++
                Log.i("Activity","Service connected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i("Activity","Service disconnected")
            }

        })*/
    }
}
