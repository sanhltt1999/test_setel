package com.example.setel.di

import android.app.Application
import android.content.Context
import com.example.setel.data.remote.ApiService
import com.example.setel.data.repository.AppRepository
import com.example.setel.data.repository.AppRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideAppRepositoryInterface(
        appServiceApi: ApiService,
    ): AppRepositoryInterface {
        return AppRepository(
            appServiceApi
        )
    }
}
