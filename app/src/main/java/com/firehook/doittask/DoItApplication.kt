package com.firehook.doittask

import android.app.Application
import com.firehook.doittask.injection.component.ApplicationComponent
import com.firehook.doittask.injection.component.DaggerApplicationComponent
import com.firehook.doittask.injection.module.DatabaseModule
import com.firehook.doittask.injection.module.NetworkModule
import com.firehook.doittask.injection.module.PreferenceModule
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

class DoItApplication : Application() {

    companion object {
        lateinit var sAppComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        sAppComponent = DaggerApplicationComponent.builder()
            .preferenceModule(PreferenceModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule(this))
            .build()

        Timber.plant(FirehookTimberTree())
        Timber.d("APPLICATION")
    }
}