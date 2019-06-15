package com.govardhan.nytimesarticles.ui.nytarticles



sealed class NYTArticlesViewState

data class ShowMessageViewState(val message: String) : NYTArticlesViewState()




