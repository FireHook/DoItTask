package com.firehook.doittask.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.firehook.doittask.mvp.model.NetworkModel
import com.firehook.doittask.mvp.model.PreferenceModel
import com.firehook.doittask.mvp.view.AuthorizationView
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

@InjectViewState
class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {

    private var mPreferenceModel = PreferenceModel()
    private var mNetworkModel = NetworkModel()

    fun auth() {
        val token = mPreferenceModel.getToken()
        Timber.d("TOKEN: %s", token)
        if (token != null) {
            val disposable = mNetworkModel.getTasks(token)
                .subscribe({
                    viewState.openMainScreen(ArrayList(it.taskList))
                }, {
                    viewState.openLoginScreen()
                })
        } else {
            viewState.openLoginScreen()
        }
    }

    fun logIn(email: String, password: String) {
        val disposable = mNetworkModel.auth(email, password)
            .subscribe({
                Timber.d("TOKEN: %s", it.token)
                mPreferenceModel.saveToken(it.token)
                auth()
            }, {
                viewState.showNetworkError(it.message)
                Timber.d("ERROR: :%s", it.message)
            })
    }

    fun register(email : String, password : String) {
        val disposable = mNetworkModel.auth(email, password)
            .subscribe({
                Timber.d("TOKEN: %s", it.token)
                mPreferenceModel.saveToken(it.token)
                auth()
            }, {
                viewState.showNetworkError(it.message)
                Timber.d("ERROR: :%s", it.message)
            })
    }
}