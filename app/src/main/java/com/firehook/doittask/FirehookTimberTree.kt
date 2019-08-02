package com.firehook.doittask

import timber.log.Timber
import java.util.*

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

class FirehookTimberTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(Locale.getDefault(), "[TIMBER] %s.%s() [#%d]",
            super.createStackElementTag(element), element.methodName, element.lineNumber)
    }
}