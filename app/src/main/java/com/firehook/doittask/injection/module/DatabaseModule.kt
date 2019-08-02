package com.firehook.doittask.injection.module

import android.arch.persistence.room.Room
import android.content.Context
import com.firehook.doittask.database.DoItDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by Vladyslav Bondar on 30.07.2019
 * Skype: diginital
 */

@Module
class DatabaseModule(val context: Context) {

    @Provides
    fun provideDatabase() : DoItDatabase {
        return Room.databaseBuilder(context, DoItDatabase::class.java, "DoItDatabase").build()
    }

}