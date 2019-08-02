package com.firehook.doittask.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Vladyslav Bondar on 22.07.2019
 * Skype: diginital
 */

data class ResponseBody (
    @SerializedName("tasks") @Expose var taskList : List<Task>,
    @SerializedName("meta")  @Expose var meta : Meta
)