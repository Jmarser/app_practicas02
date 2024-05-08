package com.jmarser.app_practicas02.domain.repository

import com.jmarser.app_practicas02.data.remote.modelsDTO.UserDto
import com.jmarser.app_practicas02.data.remote.repository.Resource

/**
 * Project: App_Practicas02
 * File: AppRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

interface AppRepository {

    suspend fun getAllUsers(): Resource<List<UserDto>>
}