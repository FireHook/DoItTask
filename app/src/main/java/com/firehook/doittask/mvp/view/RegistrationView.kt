package com.firehook.doittask.mvp.view

import com.arellomobile.mvp.MvpView

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

interface RegistrationView : MvpView {
    fun showNetworkError(message: String?)
    fun openTaskListScreen()
}