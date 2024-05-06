package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.UsersResponseDto
import com.jmarser.app_practicas02.domain.modelsDM.ListUsersDm

/**
 * Project: App_Practicas02
 * File: UsersResponseDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */
 
fun UsersResponseDto.transformToListUsersDm() =
    ListUsersDm(
        data = this.data?.map {
            it.transformToUserDm()
        }
    )