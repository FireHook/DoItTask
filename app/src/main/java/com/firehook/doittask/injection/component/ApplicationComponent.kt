package com.firehook.doittask.injection.component

import com.firehook.doittask.injection.module.DatabaseModule
import com.firehook.doittask.injection.module.NetworkModule
import com.firehook.doittask.injection.module.PreferenceModule
import com.firehook.doittask.mvp.model.DatabaseModel
import com.firehook.doittask.mvp.model.NetworkModel
import com.firehook.doittask.mvp.model.PreferenceModel
import com.firehook.doittask.mvp.view.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

@Singleton
@Component (modules = [(NetworkModule::class), (PreferenceModule::class), (DatabaseModule::class)])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(preferenceModel: PreferenceModel)
    fun inject(networkModel: NetworkModel)
    fun inject(databaseModel: DatabaseModel)

}