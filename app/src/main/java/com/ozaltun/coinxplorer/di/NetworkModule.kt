package com.ozaltun.coinxplorer.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ozaltun.coinxplorer.BuildConfig
import com.ozaltun.coinxplorer.BuildConfig.BASE_URL
import com.ozaltun.coinxplorer.data.remote.CoinxplorerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
    @Provides
    @Singleton
    fun provideApi(
        application: Application,
        builder:Retrofit.Builder,
        okHttpClientBuilder:OkHttpClient.Builder,
        converterFactory: Converter.Factory,
    ): CoinxplorerApi {
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(ChuckerInterceptor(application))
        }
        val client = okHttpClientBuilder.build()
        return builder.client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(CoinxplorerApi::class.java)
    }
}