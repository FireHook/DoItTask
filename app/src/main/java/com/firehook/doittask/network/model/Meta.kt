package com.firehook.doittask.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Vladyslav Bondar on 22.07.2019
 * Skype: diginital
 */

data class Meta (
    @SerializedName("current") @Expose var current : Int,
    @SerializedName("limit")   @Expose var limit : Int,
    @SerializedName("count")   @Expose var count : Int
)