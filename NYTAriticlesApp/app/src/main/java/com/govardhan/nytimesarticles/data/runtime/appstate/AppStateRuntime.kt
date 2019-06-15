package com.govardhan.nytimesarticles.data.runtime.appstate

import org.rekotlin.StateType

data class AppStateRuntime(
        val isForeground: Boolean = false,
        val serviceIsRunning: Boolean = false
) : StateType