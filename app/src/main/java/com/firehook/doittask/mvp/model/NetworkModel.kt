package com.firehook.doittask.mvp.model

import com.firehook.doittask.DoItApplication
import com.firehook.doittask.network.model.*
import com.firehook.doittask.network.retrofit.DoItService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class NetworkModel {

    @Inject lateinit var mService : DoItService

    init {
        DoItApplication.sAppComponent.inject(this)
    }

    fun auth(email : String, password : String) : Observable<Token> {
        return mService.auth(email, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getTasks(token: String) : Observable<ResponseBody> {
        Timber.d("%s", "Bearer $token")
        return mService.tasks("Bearer $token").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getSortedTasks(token: String, property: String, filter: String) : Observable<ResponseBody> {
        Timber.d("Query: %s", "$property%20$filter")
        return  mService.getSortedTasks(token, "$property%20$filter").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteTask(id: Int, token: String) : Observable<Array<String>> {
        return mService.deleteTask(token, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun createTask(token: String, requestTask: RequestTask): Observable<ResponsedTask> {
        return mService.createTask(token, requestTask).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun updateTask(token: String, id: Int?, requestTask: RequestTask): Observable<Array<ResponsedTask>> {
        return mService.updateTask(token, id, requestTask).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getTask(token: String, id: Int): Observable<ResponsedTask> {
        return mService.getTask(token, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}