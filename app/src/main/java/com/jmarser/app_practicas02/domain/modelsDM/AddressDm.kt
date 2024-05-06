package com.jmarser.app_practicas02.domain.modelsDM

data class AddressDm(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: GeoDm?
)
