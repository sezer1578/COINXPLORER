package com.ozaltun.coinxplorer.di

import android.app.Application
import com.ozaltun.coinxplorer.util.worker.WorkerImpl
import com.ozaltun.coinxplorer.util.worker.WorkerInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideWorkerProvider(application: Application): WorkerInterface {
        return WorkerImpl(application)
    }
}