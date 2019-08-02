package com.firehook.doittask.mvp.model

import android.content.SharedPreferences
import com.firehook.doittask.DoItApplication
import com.firehook.doittask.constants.tokenKey
import javax.inject.Inject

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class PreferenceModel {

    @Inject lateinit var mPreferences : SharedPreferences

    init {
        DoItApplication.sAppComponent.inject(this)
    }

    fun getToken() : String? {
        return mPreferences.getString(tokenKey, null)
    }

    fun saveToken(token : String) {
        mPreferences.edit().putString(tokenKey, token).apply()
    }

}