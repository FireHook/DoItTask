package com.firehook.doittask.mvp.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.firehook.doittask.R
import com.firehook.doittask.adapter.ReminderAdapter
import com.firehook.doittask.adapter.TaskAdapter
import com.firehook.doittask.constants.taskKey
import com.firehook.doittask.constants.taskListKey
import com.firehook.doittask.network.model.Task
import com.firehook.doittask.service.ReminderService
import kotlinx.android.synthetic.main.fragment_reminders.*
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

class ReminderFragment: MvpAppCompatFragment() {

    private var mTaskList = null
    private var mAdapter = null

    private val mReminderCheckedListener = object : ReminderAdapter.OnReminderCheckedListener {
        override fun onReminderChecked(task: Task) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reminder_recycler.layoutManager = LinearLayoutManager(context)
        reminder_recycler.adapter = mAdapter
        val intent = Intent(context, ReminderService::class.java)
        intent.putParcelableArrayListExtra(taskListKey, mTaskList)
        activity?.startService(intent)
    }
}