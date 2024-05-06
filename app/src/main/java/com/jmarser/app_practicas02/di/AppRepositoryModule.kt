package com.jmarser.app_practicas02.di

import com.jmarser.app_practicas02.data.remote.repository.AppRepositoryImpl
import com.jmarser.app_practicas02.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Project: App_Practicas02
 * File: AppRepositoryModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

@Module
@InstallIn(ViewModelComponent::class)
interface AppRepositoryModule {

    @Binds
    fun provideAppRepository(
        repo: AppRepositoryImpl
    ): AppRepository
}