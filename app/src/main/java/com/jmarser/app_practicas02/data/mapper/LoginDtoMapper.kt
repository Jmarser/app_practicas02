package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.LoginDto
import com.jmarser.app_practicas02.domain.modelsDM.LoginDm

/**
 * Project: App_Practicas02
 * File: LoginDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */
 
fun LoginDto.transformToLoginDm() =
    LoginDm(
        uuid = this.uuid ?: "",
        username = this.username ?: "",
        password = this.password ?: "",
        md5 = this.md5 ?: "",
        sha1 = this.sha1 ?: "",
        registered = this.registered ?: ""
    )