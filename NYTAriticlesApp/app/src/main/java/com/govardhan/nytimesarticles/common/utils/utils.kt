package com.govardhan.nytimesarticles.common.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.databinding.BindingAdapter
import android.os.Build
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.govardhan.nytimesarticles.R
import com.govardhan.nytimesarticles.data.remote.NetworkingController
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog.*
import retrofit2.HttpException
import java.util.ArrayList


fun isM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun <T> singleRequest(request: Single<T>, state: NetworkingController): Single<T> =
    Single.just(state.isNetworkConnect)
        .flatMap {
            if (!it) throw Throwable(state.networkConnectErrorMessage)
            else request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { err ->
                    val httpError = err as HttpException
                }
        }


fun showTextDialog(context: Context, message: String, dismissCallback: (dialog: DialogInterface) -> Unit = {}) {
    val dialog = Dialog(context, R.style.DialogTransparent)
    dialog.setContentView(R.layout.dialog)
    dialog.tvMessage.text = message
    dialog.tvMessage.setOnClickListener { dialog.dismiss() }
    dialog.setOnDismissListener { dismissCallback(it) }
    dialog.show()
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("bind:circularImageUrl")
fun loadCircularImage(view: ImageView, imageUrl: String) {
    Glide.with(view.getContext())
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view);
}

@BindingAdapter("bind:setWebViewClient")
fun setWebViewClient(view: WebView, client: WebViewClient) {
    view.webViewClient = client
    view.clearCache(true);
    view.clearHistory();
    view.getSettings().setJavaScriptEnabled(true);
}

@BindingAdapter("bind:loadUrl")
fun loadUrl(view: WebView, url: String? = "") {
    view.loadUrl(url)
}


@BindingAdapter("bind:setVsibility")
fun setViewVisibility(view: View, visibility: Boolean) {
    when (visibility) {
        true -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }

}


fun getImageUrlFromArticle(nytArticleItem: NYTArticleItem, imageSize: String): String {
    if (!nytArticleItem.media.isNullOrEmpty()) {
        val mediaItemList = nytArticleItem.media
        mediaItemList.get(0)?.let {
            if (!it.mediaMetadata.isNullOrEmpty()) {
                val mediaMetaDataList = it.mediaMetadata
                for (mediaMetaDataItem in mediaMetaDataList) {
                    mediaMetaDataItem?.format?.let {
                        if (it.equals(imageSize)) {
                            return mediaMetaDataItem.url.toString()
                        }
                    }
                }
            }
        }
    }
    return ""
}

fun getDataFromUnknownType(data: Any): String {
    var filteredData: String = ""
    when (data) {
        is String -> {
            filteredData = data.toString()
        }
        is ArrayList<*> -> {
            filteredData = (data as ArrayList<String>).toString().replace("[","").replace("]","")
        }
    }

    return filteredData

}






