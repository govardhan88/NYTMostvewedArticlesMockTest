package com.govardhan.nytimesarticles.data.runtime.appstate

import org.rekotlin.Action

fun foregroundReducer(action: Action, stateRuntime: AppStateRuntime?): AppStateRuntime {
    var state = stateRuntime ?: AppStateRuntime()

    when (action) {
        is ForegroundStateAction -> {
            state = state.copy(isForeground = action.isForeground)
        }
    }

    return state
}