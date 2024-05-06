package com.jmarser.app_practicas02.domain.modelsDM

data class UserDm(
    val id: Int?,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val birthDate: String?,
    val login: LoginDm?,
    val address: AddressDm?,
    val phone: String?,
    val website: String?,
    val company: CompanyDm?
)
