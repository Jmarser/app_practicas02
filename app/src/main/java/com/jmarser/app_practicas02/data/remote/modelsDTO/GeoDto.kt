package com.jmarser.app_practicas02.data.remote.modelsDTO


import com.google.gson.annotations.SerializedName

data class GeoDto(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)