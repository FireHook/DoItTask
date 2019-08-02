package com.firehook.doittask.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

data class ResponsedTask (
    @SerializedName("task") @Expose var task: Task
)