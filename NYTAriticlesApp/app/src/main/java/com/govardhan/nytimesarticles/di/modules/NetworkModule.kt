package com.govardhan.nytimesarticles.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.govardhan.nytimesarticles.BuildConfig
import com.govardhan.nytimesarticles.common.EnvironmentState
import com.govardhan.nytimesarticles.data.remote.NetworkingController
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor? = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else null

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor?): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        if (interceptor != null) okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor { chain ->
            val newChain = chain.request().newBuilder()
            chain.proceed(newChain.build())
        }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideEnvironmentState(): EnvironmentState = EnvironmentState


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Singleton
    fun provideNetworkingController(context: Context,
                                    retrofitBuilder: Retrofit.Builder,
                                    environment: EnvironmentState): NetworkingController =
            NetworkingController(context, retrofitBuilder, environment)

}