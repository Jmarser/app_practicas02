package com.jmarser.app_practicas02.data.remote.repository

/**
 * Project: App_Practicas02
 * File: Resource
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024

 * La clase sellada `Resource` representa los estados de resultado de operaciones, facilitando un manejo explícito y seguro de los éxitos, fallos, y operaciones en progreso.
 * Esta estructura es ideal para operaciones que pueden tener un resultado exitoso, fallido o que están en proceso, como solicitudes de red o procesamientos de datos complejos, permitiendo un manejo de estado claro y un flujo de control robusto en la aplicación.
 *
 * Subclases:
 * - `Success`: Representa el resultado exitoso de una operación, conteniendo el valor resultante.
 * - `Failure`: Encapsula información detallada sobre un fallo en la operación.
 * - `Loading`: Indica que la operación está en curso y aún no ha finalizado.
 */
sealed class Resource<out T> {

    /**
     * Representa el resultado exitoso de una operación, conteniendo el valor de tipo `T`.
     *
     * @param T El tipo de dato del valor resultante de la operación exitosa.
     * @property value El valor resultante de la operación exitosa.
     */
    data class Success<out T>(val value: T) : Resource<T>()

    /**
     * Encapsula la información de un fallo en la operación, incluyendo si el error fue debido a problemas de red, un código de error opcional, y un mensaje de error opcional.
     *
     * @property isNetworkError Indica si el fallo fue debido a un error de red.
     * @property errorCode El código de error específico de la falla, si es aplicable y disponible.
     * @property errorMessage El mensaje descriptivo del error, proporcionando más detalles sobre la naturaleza del fallo.
     */
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: String?
    ) : Resource<Nothing>()

    /**
     * Representa el estado de una operación en curso. Este estado es útil para indicar que una tarea está siendo procesada y aún no se ha completado.
     * Es ideal para mostrar indicadores de carga en la interfaz de usuario durante operaciones asíncronas o de larga duración.
     */
    object Loading : Resource<Nothing>()
}