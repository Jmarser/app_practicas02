package com.jmarser.app_practicas02.data.mapper

import com.jmarser.app_practicas02.data.remote.modelsDTO.GeoDto
import com.jmarser.app_practicas02.domain.modelsDM.GeoDm

/**
 * Project: App_Practicas02
 * File: GeoDtoMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/05/2024
 */
 
fun GeoDto.transformToGeoDm() =
    GeoDm(
        lat = this.lat ?: "0.0",
        lng = this.lng ?: "0.0",
    )