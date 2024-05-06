package com.jmarser.app_practicas02.presentation.core

import android.content.Context

/**
 * Project: App_Practicas02
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024

 * `ResourceProviderImpl` es una implementación concreta de `ResourceProvider` que utiliza el contexto de la aplicación para acceder a recursos como cadenas de texto.
 * Esta clase permite un acceso desacoplado y testeable a los recursos de la aplicación, facilitando su uso en diferentes partes del código sin depender directamente del contexto de Android.
 *
 * @property context El contexto de la aplicación utilizado para acceder a los recursos.
 */
class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    /**
     * Devuelve una cadena de texto correspondiente al identificador de recurso proporcionado.
     * Utiliza el contexto de la aplicación para acceder al recurso.
     *
     * @param id El identificador del recurso de cadena de texto.
     * @return La cadena de texto correspondiente al recurso.
     */
    override fun getString(id: Int): String = context.getString(id)
}