package com.firehook.doittask.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.firehook.doittask.network.model.Task

/**
 * Created by Vladyslav Bondar on 30.07.2019
 * Skype: diginital
 */

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class DoItDatabase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao
}