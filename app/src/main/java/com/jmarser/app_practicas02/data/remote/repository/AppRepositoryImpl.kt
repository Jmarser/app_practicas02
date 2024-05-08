package com.jmarser.app_practicas02.data.remote.repository

import com.jmarser.app_practicas02.data.remote.network.ApiService
import com.jmarser.app_practicas02.data.remote.network.SafeApiCall
import com.jmarser.app_practicas02.domain.repository.AppRepository
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: AppRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AppRepository, SafeApiCall {

    override suspend fun getAllUsers() = safeApiCall {
        apiService.getAllUsers()
    }
}