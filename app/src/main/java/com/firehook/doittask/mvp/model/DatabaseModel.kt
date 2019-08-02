package com.firehook.doittask.mvp.model

import com.firehook.doittask.DoItApplication
import com.firehook.doittask.database.DoItDatabase
import javax.inject.Inject

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

class DatabaseModel {

    @Inject lateinit var mDatabase: DoItDatabase

    init {
        DoItApplication.sAppComponent.inject(this)
    }



}