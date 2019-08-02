package com.firehook.doittask.mvp.view

import com.arellomobile.mvp.MvpView
import com.firehook.doittask.network.model.Task
import java.util.ArrayList

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

interface AuthorizationView : MvpView {
    fun openLoginScreen() {}
    fun openRegistrationScreen() {}
    fun openMainScreen(taskList: ArrayList<Task>)
    fun showNetworkError(message: String?)
    fun startSplash()
    fun stopSplash()
}