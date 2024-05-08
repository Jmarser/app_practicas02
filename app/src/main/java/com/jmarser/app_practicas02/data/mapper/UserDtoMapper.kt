package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.UserDto
import com.jmarser.app_practicas02.domain.modelsDM.UserDm

/**
 * Project: App_Practicas02
 * File: UserDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */


fun List<UserDto>.tansformToListOfUserDm(): List<UserDm>{
    return this.map {
        it.transformToUserDm()
    }
}

fun UserDto.transformToUserDm() =
    UserDm(
        id = this.id ?: 0,
        firstname = this.firstname ?: "",
        lastname = this.lastname ?: "",
        email = this.email ?: "",
        address = this.address?.transformToAddressDm(),
        phone = this.phone ?: "",
        website = this.website ?: "",
        company = this.company?.transformToCompanyDm(),
    )

