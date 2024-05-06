package com.jmarser.app_practicas02.data.remote.modelsDTO


import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("md5")
    val md5: String?,
    @SerializedName("sha1")
    val sha1: String?,
    @SerializedName("registered")
    val registered: String?
)