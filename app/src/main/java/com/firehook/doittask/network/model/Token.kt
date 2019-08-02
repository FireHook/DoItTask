package com.firehook.doittask.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Vladyslav Bondar on 20.07.2019
 * Skype: diginital
 */

data class Token (
    @SerializedName("token") @Expose var token : String
)