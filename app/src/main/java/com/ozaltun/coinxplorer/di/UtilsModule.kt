package com.ozaltun.coinxplorer.di

import android.app.Application
import androidx.work.WorkManager
import com.ozaltun.coinxplorer.util.worker.WorkerImpl
import com.ozaltun.coinxplorer.util.worker.WorkerInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideWorkerInterface(application: Application): WorkerInterface {
        return WorkerImpl(application)
    }
    @Provides
    @Singleton
    @Named("IO")
    fun provideCoContextIO(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    @Named("Default")
    fun provideCoContextDefault(): CoroutineDispatcher = Dispatchers.Default
}