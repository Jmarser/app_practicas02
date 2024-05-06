package com.jmarser.app_practicas02.presentation.core

import androidx.annotation.StringRes

/**
 * Project: App_Practicas02
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024

 * `ResourceProvider` es una interfaz que define métodos para acceder a recursos de la aplicación, como cadenas de texto.
 * Esta abstracción permite un acceso más flexible y testeable a recursos sin depender directamente del contexto de Android.
 */
interface ResourceProvider {

    /**
     * Devuelve una cadena de texto correspondiente al identificador de recurso proporcionado.
     *
     * @param id El identificador del recurso de cadena de texto.
     * @return La cadena de texto correspondiente al recurso.
     */
    fun getString(@StringRes id: Int): String
}