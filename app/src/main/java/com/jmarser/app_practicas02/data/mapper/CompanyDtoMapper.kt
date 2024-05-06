package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.CompanyDto
import com.jmarser.app_practicas02.domain.modelsDM.CompanyDm

/**
 * Project: App_Practicas02
 * File: CompanyDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */
 
fun CompanyDto.transformToCompanyDm() =
    CompanyDm(
        name = this.name ?: "",
        catchPhrase = this.catchPhrase ?: "",
        bs = this.bs ?: ""
    )