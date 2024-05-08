package com.jmarser.app_practicas02.data.remote.modelsDTO


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val firstname: String?,
    @SerializedName("username")
    val lastname: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("address")
    val address: AddressDto?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("company")
    val company: CompanyDto?
)