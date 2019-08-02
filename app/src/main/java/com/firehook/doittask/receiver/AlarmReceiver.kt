package com.firehook.doittask.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 01.08.2019
 * Skype: diginital
 */

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("RECEIVE")
    }

}