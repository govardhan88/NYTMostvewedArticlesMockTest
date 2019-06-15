package com.govardhan.nytimesarticles.common.utils

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.govardhan.nytimesarticles.BR


class WebviewObserver : BaseObservable() {
    internal var hideProgress = false

    val webViewClient: WebViewClient
        get() = Client()

    var isHideProgress: Boolean
        @Bindable
        get() = hideProgress
        private set(hideProgress) {
            this.hideProgress = hideProgress
            notifyPropertyChanged(BR.hideProgress)
        }

    private inner class Client : WebViewClient() {
        override fun onReceivedError(
            view: WebView, request: WebResourceRequest,
            error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
            isHideProgress = true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            isHideProgress = true
        }
    }
}