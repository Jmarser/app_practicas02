package com.jmarser.app_practicas02.di

/**
 * Project: App_Practicas02
 * File: NetworkModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

import com.jmarser.app_practicas02.presentation.core.ValidationFormService
import com.jmarser.app_practicas02.presentation.core.ValidationFormServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ValidationFormModule {
    @Provides
    fun provideValidationFormService(): ValidationFormService = ValidationFormServiceImpl()
}