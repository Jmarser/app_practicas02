package com.jmarser.app_practicas02.presentation.user_screen.state

import com.jmarser.app_practicas02.domain.modelsDM.UserDm

sealed class UsersState{

    object Idle: UsersState()
    object Loading: UsersState()
    data class Success(
        val data: List<UserDm>,
        val message: String? = null
    ): UsersState()
    data class Failure(val message: String): UsersState()
}
