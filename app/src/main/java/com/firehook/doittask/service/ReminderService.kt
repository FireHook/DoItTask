package com.firehook.doittask.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.firehook.doittask.constants.taskListKey
import com.firehook.doittask.network.model.Task
import com.firehook.doittask.receiver.AlarmReceiver
import java.util.concurrent.Executors

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

class ReminderService: Service() {

    private val mExecutor = Executors.newCachedThreadPool()
    lateinit var mAlarmManager: AlarmManager
    private val requestCode = 777

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val taskList = intent?.getParcelableArrayListExtra<Task>(taskListKey)
        if (taskList != null) {
            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT)
            for (i in 0 until taskList.size) {
                mExecutor.submit {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, taskList[i].expiration, pendingIntent)
                    } else {
                        //TODO Handle for previous versions
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    public fun startReminder(task: Task) {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT)
        mExecutor.submit {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, task.expiration, pendingIntent)
            } else {
                //TODO Handle for previous versions
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? { return null }
}