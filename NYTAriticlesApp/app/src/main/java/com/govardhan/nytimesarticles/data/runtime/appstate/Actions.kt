package com.govardhan.nytimesarticles.data.runtime.appstate

import org.rekotlin.Action

data class ForegroundStateAction(val isForeground: Boolean) : Action
