package com.firehook.doittask.injection.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.firehook.doittask.DoItApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

@Module
class PreferenceModule(private val application: DoItApplication) {

    @Provides @Singleton fun provideApplicationContext() : Context {
        return application.applicationContext
    }

    @Provides @Singleton fun providePreferences(context: Context) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}