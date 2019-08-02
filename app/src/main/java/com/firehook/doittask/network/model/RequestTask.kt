package com.firehook.doittask.network.model

/**
 * Created by Vladyslav Bondar on 30.07.2019
 * Skype: diginital
 */

data class RequestTask (
    var title: String,
    var dueBy: Long,
    var priority: String
)