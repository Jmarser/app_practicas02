package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.AddressDto
import com.jmarser.app_practicas02.domain.modelsDM.AddressDm

/**
 * Project: App_Practicas02
 * File: AddressDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */
 
fun AddressDto.transformToAddressDm() =
    AddressDm(
        street = this.street ?: "",
        city = this.city ?: "",
        suite = this.suite ?: "",
        zipcode = this.zipcode ?: "",
        geo = this.geo?.transformToGeoDm()
    )