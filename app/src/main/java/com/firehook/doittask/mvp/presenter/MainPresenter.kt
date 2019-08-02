package com.firehook.doittask.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.firehook.doittask.mvp.model.NetworkModel
import com.firehook.doittask.mvp.model.PreferenceModel
import com.firehook.doittask.mvp.view.MainView
import com.firehook.doittask.network.model.RequestTask
import com.firehook.doittask.network.model.Task
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.ArrayList

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private var mPreferenceModel = PreferenceModel()
    private var mNetworkModel = NetworkModel()

    fun showTasks() {
        val token = mPreferenceModel.getToken()
        if (token != null) {
            val disposable = mNetworkModel.getTasks(token)
                .subscribe({
                    Timber.d("Task list: %s", it.taskList)
                    viewState.openTaskListScreen(ArrayList(it.taskList))
                }, {
                    Timber.d("ERROR: %s", it.message)
                })
        }
    }

    fun refreshTasks() {
        val token = mPreferenceModel.getToken()
        if (token != null) {
            val disposable = mNetworkModel.getTasks(token)
                .subscribe({
                    Timber.d("Task list: %s", it.taskList)
                    viewState.onTaskLoaded(ArrayList(it.taskList))
                }, {
                    Timber.d("ERROR: %s", it.message)
                    viewState.showNetworkError(it.message)
                })
        }
    }

    fun sort(property: String, filter: String) {
        val token = "Bearer ${mPreferenceModel.getToken()}"
        Timber.d("Token: %s", token)
        val disposable = mNetworkModel.getSortedTasks(token, property, filter).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("Task list: %s", it.taskList)
                viewState.onTaskLoaded(ArrayList(it.taskList))
            }, {
                Timber.d("ERROR: %s", it.message)
                viewState.showNetworkError(it.message)
            })
    }

    fun deleteTask(id: Int) {
        val token = "Bearer ${mPreferenceModel.getToken()}"
        Timber.d("Token: %s", token)
        val disposable = mNetworkModel.deleteTask(id, token).subscribe({
            Timber.d("DELETED: %s", it)
            viewState.closeScreen()
        }, {
            Timber.d("ERROR: %s", it.message)
            viewState.showNetworkError(it.message)
        })
    }

    fun createTask() {
        val token = "Bearer ${mPreferenceModel.getToken()}"
        Timber.d("Token: %s", token)
        val disposable = mNetworkModel.createTask(token, RequestTask("New meeting", 1549477494, "High"))
            .subscribe({
                Timber.d("CREATED TASK: %s", it.task)
                viewState.notifyRecyclerChanged(it.task)
            }, {
                Timber.d("ERROR: %s", it.message)
                viewState.showNetworkError(it.message)
            })
    }

    fun editTask(id: Int?, title: String, dueBy: String, priority: Int) {
        val priorityStr = when(priority) {
            0 -> { "High" }
            1 -> { "Normal" }
            2 -> { "Low" }
            else -> { "err" }
        }
        val token = "Bearer ${mPreferenceModel.getToken()}"
        Timber.d("Token: %s", token)
        Timber.d("id: %s", id)
        Timber.d("radio id: %s", priority)
        Timber.d("RequestTask: %s", RequestTask(title, 1549477494, priorityStr))
        val disposable = mNetworkModel.updateTask(token, id, RequestTask(title, 1549477494, priorityStr))
            .subscribe({
                Timber.d("UPDATED: %s", it)
                viewState.closeScreen()
            }, {
                Timber.d("ERROR: %s", it.message)
                viewState.showNetworkError(it.message)
            })
    }

    fun getTask(id: Int) {
        val token = "Bearer ${mPreferenceModel.getToken()}"
        Timber.d("Token: %s", token)
        val disposable = mNetworkModel.getTask(token, id).subscribe({
            Timber.d("Task: %s", it.task)
            viewState.onTaskLoaded(arrayListOf(it.task))
        }, {
            Timber.d("ERROR: %s", it.message)
            viewState.showNetworkError(it.message)
        })
    }
}