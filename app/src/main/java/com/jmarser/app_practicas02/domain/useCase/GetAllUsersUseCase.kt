package com.jmarser.app_practicas02.domain.useCase

import com.jmarser.app_practicas02.data.mapper.tansformToListOfUserDm
import com.jmarser.app_practicas02.data.remote.repository.Resource
import com.jmarser.app_practicas02.domain.repository.AppRepository
import com.jmarser.app_practicas02.presentation.user_screen.state.UsersState
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: GetAllUsersUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 07/05/2024
 */

class GetAllUsersUseCase @Inject constructor(
    private val repo: AppRepository
) {

    suspend fun getAllUsers(): UsersState{
        return when (val response = repo.getAllUsers()){
            Resource.Loading -> {
                UsersState.Loading
            }

            is Resource.Success -> {
                UsersState.Success(response.value.tansformToListOfUserDm())
            }
            is Resource.Failure -> {
                if (response.isNetworkError){
                    UsersState.Failure("Error de red: ${response.errorMessage}")
                }else{
                    UsersState.Failure("Error: ${response.errorCode} / ${response.errorMessage}")
                }
            }
        }
    }
}