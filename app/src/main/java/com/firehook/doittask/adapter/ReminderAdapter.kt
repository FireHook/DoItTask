package com.firehook.doittask.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firehook.doittask.R
import com.firehook.doittask.network.model.Task
import com.firehook.doittask.util.DateUtil
import kotlinx.android.synthetic.main.reminder_item.view.*
import kotlinx.android.synthetic.main.task_item.view.*
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

class ReminderAdapter(var taskList : MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var mItemClickListener : OnTaskClickListener? = null
    private var mItemCheckedListener: OnReminderCheckedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        Timber.d("LIST: %s", taskList)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_reminders, parent, false)
        return TaskAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.reminder_title.text = task.name
        holder.itemView.reminder_expiration.text = DateUtil.convertDate(task.expiration)
        holder.itemView.switch_reminder.isChecked = task.isReminder
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null) mItemClickListener?.onTaskClicked(task)
        }
        holder.itemView.switch_reminder.setOnCheckedChangeListener { _, isChecked ->
            task.isReminder = isChecked
            if (mItemCheckedListener != null) mItemCheckedListener?.onReminderChecked(task)
        }
    }

    fun setItemClickListener(itemClickListener: OnTaskClickListener?) {
        mItemClickListener = itemClickListener
    }

    fun setItemCheckedListener(itemCheckedListener: OnReminderCheckedListener?) {
        mItemCheckedListener = itemCheckedListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnTaskClickListener {
        fun onTaskClicked(task: Task)
    }

    interface OnReminderCheckedListener {
        fun onReminderChecked(task: Task)
    }
}