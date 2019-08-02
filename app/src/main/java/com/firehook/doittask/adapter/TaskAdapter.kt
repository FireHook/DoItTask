package com.firehook.doittask.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.firehook.doittask.R
import com.firehook.doittask.network.model.Task
import com.firehook.doittask.util.DateUtil
import kotlinx.android.synthetic.main.task_item.view.*

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

class TaskAdapter(var taskList : MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var mItemClickListener : OnTaskClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.task_item_name_textview.text = task.name
        holder.itemView.task_item_expiration_textview.text = DateUtil.convertDate(task.expiration)
        holder.itemView.task_item_priority_textview.text = task.priority
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null) mItemClickListener?.onTaskClicked(task)
        }
    }

    fun setItemClickListener(itemClickListener: OnTaskClickListener?) {
        mItemClickListener = itemClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnTaskClickListener {
        fun onTaskClicked(task: Task)
    }
}