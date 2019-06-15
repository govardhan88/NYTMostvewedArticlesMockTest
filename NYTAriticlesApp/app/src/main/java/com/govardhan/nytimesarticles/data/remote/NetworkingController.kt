package com.govardhan.nytimesarticles.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.govardhan.nytimesarticles.R
import com.govardhan.nytimesarticles.common.*
import retrofit2.Retrofit
import javax.inject.Inject


class NetworkingController
@Inject constructor(private val context: Context,
                    private val retrofitBuilder: Retrofit.Builder,
                    private val environment: EnvironmentState) {

    lateinit var netApi: NetApi

    init {
        updateNetApi(environment.currentState)
    }

    fun getContect() = context

    fun updateNetApi(environment: Environment) {
        netApi = retrofitBuilder
                .baseUrl(getBaseUrl(environment))
                .build()
                .create(NetApi::class.java)
    }

    fun getBaseUrl(environment: Environment): String =
            when (environment) {
                Environment.PRODUCTION -> {
                    BASE_PROD_URL
                }
                Environment.DEV -> {
                    BASE_DEV_URL
                }
                Environment.QA->{
                    BASE_QA_URL
                }
            }

    val isNetworkConnect: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo?.isConnected == true
        }

    val networkConnectErrorMessage: String
        get() = context.getString(R.string.error_network_connect)
}
