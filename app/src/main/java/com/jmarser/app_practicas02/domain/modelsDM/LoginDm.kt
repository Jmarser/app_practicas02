package com.jmarser.app_practicas02.domain.modelsDM

data class LoginDm(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val md5: String?,
    val sha1: String?,
    val registered: String?
)
