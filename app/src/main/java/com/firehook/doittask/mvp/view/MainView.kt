package com.firehook.doittask.mvp.view

import com.arellomobile.mvp.MvpView
import com.firehook.doittask.network.model.Task
import java.util.ArrayList

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

interface MainView : MvpView {
    fun openTaskListScreen(taskList: ArrayList<Task>) {}
    fun onTaskLoaded(taskList: ArrayList<Task>) {}
    fun showNetworkError(message: String?)
    fun closeScreen() {}
    fun notifyRecyclerChanged(task: Task) {}
}