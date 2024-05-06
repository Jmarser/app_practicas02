package com.jmarser.app_practicas02.data.remote.network

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import retrofit2.Response

/**
 * Project: App_Practicas02
 * File: ApiError
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 *
 * La clase `ApiError` proporciona mecanismos para analizar y manejar errores específicos que pueden surgir durante las operaciones de red y procesamiento de datos, particularmente en contextos de solicitudes HTTP y serialización/deserialización de JSON.
 * Esta clase incluye métodos para interpretar respuestas HTTP fallidas y excepciones de serialización de JSON, devolviendo mensajes de error descriptivos que facilitan la depuración y el manejo de errores en aplicaciones que consumen servicios web.
 *
 * Funciones:
 * - `parserHttpError`: Analiza errores basados en códigos de respuesta HTTP y proporciona mensajes de error específicos.
 * - `parserSerializationError`: Maneja excepciones lanzadas durante la serialización o deserialización de JSON, ofreciendo detalles sobre la naturaleza del error.
 */
object ApiError {

    /**
     * Analiza los errores HTTP basándose en el código de respuesta de una solicitud HTTP y devuelve un mensaje de error específico.
     * Esta función intenta recuperar y utilizar el cuerpo del error de la respuesta para proporcionar un contexto adicional sobre el fallo, lo cual es crucial para una depuración efectiva y una respuesta adecuada a los errores.
     *
     * @param response La respuesta HTTP que se está analizando, incluyendo tanto el cuerpo del error como el código de estado.
     * @return Un `String` que contiene el mensaje de error específico basado en el código de estado de la respuesta.
     */
    fun <T> parserHttpError(response: Response<T>): String {
        val errorBody = try {
            response.errorBody()?.string().orEmpty()
        } catch (e: Exception) {
            null
        }


        return when (response.code()) {
            400 -> "Bad Request: ${errorBody ?: "Invalid request format."}"
            401 -> "Unauthorized: ${errorBody ?: "Authentication required"}"
            403 -> "Forbidden: ${errorBody ?: "You do not have permission to access the requested resource."}"
            404 -> "Not Found: ${errorBody ?: "The requested resource was not found."}"
            408 -> "Request Timeout: ${errorBody ?: "The request took too long to complete."}"
            500 -> "Internal Server Error: ${errorBody ?: "The server encountered an unexpected condition."}"
            502 -> "Bad Gateway: ${errorBody ?: "Invalid response from an upstream server."}"
            503 -> "Service Unavailable: ${errorBody ?: "The server is not ready to handle the request."}"
            504 -> "Gateway Timeout: ${errorBody ?: "The upstream server failed to send a request in the time allowed."}"
            else -> "Unknow Error: ${errorBody}"
        }

    }

    /**
     * Enumeración de los posibles tipos de errores de JSON que pueden ser capturados durante
     * la serialización o deserialización de JSON. Este enum ayuda a categorizar los errores
     * de manera más específica, permitiendo un manejo de errores más granular y descriptivo.
     */
    enum class JsonErrorType {
        SYNTAX,    // Error de sintaxis JSON.
        PARSING,   // Error al interpretar el JSON.
        STATE,     // Estado ilegal encontrado durante el procesamiento de JSON.
        IO,        // Error de entrada/salida durante el procesamiento de JSON.
        UNKNOWN    // Tipo de error no clasificado o desconocido.
    }

    /**
     * Clase que representa un error de JSON con un mensaje descriptivo y un tipo específico.
     * Extiende de `Exception` para permitir su manejo en bloques catch como cualquier otra excepción,
     * pero proporcionando información adicional sobre el tipo de error de JSON que ocurrió.
     *
     * @param message Mensaje descriptivo del error que proporciona detalles adicionales sobre el problema.
     * @param type Tipo de error de JSON, que ayuda a identificar la naturaleza del error de forma más precisa.
     */
    class JsonError(message: String, val type: JsonErrorType) : Exception(message)

    /**
     * Analiza y maneja excepciones generadas durante la serialización o deserialización de JSON, devolviendo un `Throwable` con un mensaje de error específico.
     * Esta función categoriza las excepciones de JSON según su naturaleza, proporcionando detalles que son cruciales para la resolución de problemas y la mejora de la gestión de errores.
     *
     * @param exception La excepción capturada durante la operación de serialización o deserialización de JSON.
     * @return Un `JsonError` que encapsula un mensaje de error detallado y el tipo específico de la excepción de JSON capturada.
     */
    fun parserSerializationError(exception: Exception): JsonError {
        return when (exception) {
            is JsonSyntaxException -> JsonError(
                "JSON syntax error: ${exception.message}",
                JsonErrorType.SYNTAX
            )

            is JsonParseException -> JsonError(
                "JSON parsing error: ${exception.message}",
                JsonErrorType.PARSING
            )

            is IllegalStateException -> JsonError(
                "Illegal state during JSON parsing: ${exception.message}",
                JsonErrorType.STATE
            )

            is JsonIOException -> JsonError(
                "I/O error during JSON processing: ${exception.message}",
                JsonErrorType.IO
            )

            else -> JsonError(
                "Unknown serialization error: ${exception.message}",
                JsonErrorType.UNKNOWN
            )
        }
    }
}