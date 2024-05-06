package com.jmarser.app_practicas02.data.remote.modelsDTO


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("login")
    val login: LoginDto?,
    @SerializedName("address")
    val address: AddressDto?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("company")
    val company: CompanyDto?
)