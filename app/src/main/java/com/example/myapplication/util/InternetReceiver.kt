package com.example.myapplication.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter



/** Класс который отлавливает событие изменения состояния подключения к интернету **/
class InternetReceiver(func: ()-> Unit) {

    private val BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

     var receiver: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            when (p1?.action) {
                BROADCAST_ACTION -> func()
            }
        }
    }

     var intentFilter: IntentFilter = IntentFilter(BROADCAST_ACTION)
}