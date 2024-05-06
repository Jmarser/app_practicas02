package com.jmarser.app_practicas02.presentation.core

import android.content.SharedPreferences
import android.util.Log

/**
 * Project: App_Practicas02
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 *
 * `AppPreferences` provee una interfaz conveniente y tipada para almacenar y recuperar preferencias o datos de usuario.
 * Soporta varios tipos de datos, incluyendo String, Boolean, Float, Int, Long, y Double, simplificando el uso de `SharedPreferences`.
 * Incluye validación para prevenir almacenar valores `null` y registros para facilitar la depuración.
 *
 * @property sharedPreferences La instancia de `SharedPreferences` que será utilizada para almacenar y recuperar datos.
 */
class AppPreferences(val sharedPreferences: SharedPreferences) {

    /**
     * Guarda un valor en las preferencias compartidas, identificado por una clave (`key`).
     * Soporta varios tipos de datos mediante el uso de reificación y sobrecarga de métodos.
     * Registra un error si el valor a almacenar es `null`.
     *
     * @param T El tipo de dato del valor a almacenar.
     * @param key La clave con la cual se almacenará el valor.
     * @param value El valor a almacenar. Si el valor es `null`, no se almacenará y se registrará un error.
     */
    inline fun <reified T> save(key: String, value: T) {
        if (value == null) {
            Log.d("APP_PREFERENCES", "ERROR Se ha encontrado un valor NULL en la variable: core")
            return // Retorna tempranamente para evitar intentar guardar un valor null.
        }

        with(sharedPreferences.edit()) {
            value?.let {
                when (T::class) {
                    String::class -> putString(key, value as String)
                    Boolean::class -> putBoolean(key, value as Boolean)
                    Float::class -> putFloat(key, value as Float)
                    Int::class -> putInt(key, value as Int)
                    Long::class -> putLong(key, value as Long)
                    Double::class -> putLong(key, (value as Double).toRawBits())
                    else -> error("Tipo desconocido")
                }.apply()
            }
        }
    }

    /**
     * Recupera un valor de las preferencias compartidas, identificado por una clave (`key`).
     * Retorna un valor por defecto si la clave no existe. Asegura un retorno seguro sin arrojar excepciones para tipos no encontrados.
     *
     * @param T El tipo de dato del valor a recuperar.
     * @param key La clave del valor a recuperar.
     * @param defaultValue El valor por defecto a retornar si la clave no existe.
     * @return El valor recuperado o el valor por defecto. Asegura que siempre se retorna un valor, incluso si la clave no está presente.
     */
    inline fun <reified T> get(key: String, defaultValue: T): T {
        return when (T::class) {
            String::class -> sharedPreferences.getString(key, defaultValue as? String ?: "") as T
            Boolean::class -> sharedPreferences.getBoolean(
                key,
                defaultValue as? Boolean ?: false
            ) as T

            Float::class -> sharedPreferences.getFloat(key, defaultValue as? Float ?: 0f) as T
            Int::class -> sharedPreferences.getInt(key, defaultValue as? Int ?: 0) as T
            Long::class -> sharedPreferences.getLong(key, defaultValue as? Long ?: 0L) as T
            Double::class -> Double.fromBits(
                sharedPreferences.getLong(
                    key,
                    (defaultValue as? Double ?: 0.0).toRawBits()
                )
            ) as T // Recupera el Double
            else -> error("This type is not supported")
        }
    }

    /**
     * Limpia todas las preferencias guardadas, eliminando todos los datos almacenados en `SharedPreferences`.
     * Registra errores específicos si falla la limpieza.
     */
    fun clearAll() {
        try {
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
        } catch (e: SecurityException) {
            Log.d("CLEAR_PREFERENCES", "Error: ${e.message} / ${e.localizedMessage}")
        }
    }
}