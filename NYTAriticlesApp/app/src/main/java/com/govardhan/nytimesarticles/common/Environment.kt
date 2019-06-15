package com.govardhan.nytimesarticles.common

object EnvironmentState {
    var currentState: Environment = Environment.DEV
}

enum class Environment {
    PRODUCTION, DEV,QA
}