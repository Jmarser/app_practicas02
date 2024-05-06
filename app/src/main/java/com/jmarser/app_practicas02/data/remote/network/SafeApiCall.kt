package com.jmarser.app_practicas02.data.remote.network

import android.util.Log
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.jmarser.app_practicas02.data.remote.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Project: App_Practicas02
 * File: ApiError
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 *
 * La interfaz `SafeApiCall` proporciona un mecanismo para realizar llamadas a la API de forma segura y robusta dentro de corutinas,
 * manejando automáticamente los errores de red, errores de serialización y otros fallos comunes.
 * A través de la función `safeApiCall`, las operaciones de red se ejecutan en un contexto controlado donde sus resultados se encapsulan
 * en la clase `Resource`, permitiendo un manejo de errores coherente y facilitando la integración con la lógica de la interfaz de usuario.
 * Esta interfaz se asegura de que todas las excepciones capturadas sean transformadas en estados manejables, haciendo la gestión de errores
 * más predecible y fácil de manejar en capas superiores de la aplicación.
 */
interface SafeApiCall {

    /**
     * Ejecuta una llamada a la API de forma segura, capturando y manejando excepciones comunes para convertirlas en estados manejables de la clase `Resource`.
     * Esta función es genérica y puede manejar cualquier tipo de respuesta que se espera de la API.
     *
     * @param T El tipo de dato esperado en la respuesta de la llamada a la API.
     * @param apiCall La función suspendida que realiza la llamada a la API. Debe retornar una instancia de `Response<T>`.
     * @return Un objeto `Resource<T>` que representa el estado de la operación: éxito, fallo o en carga.
     *
     * La función `safeApiCall` utiliza el `Dispatcher.IO` para ejecutar la llamada a la API en un hilo adecuado para operaciones de I/O.
     * Si la llamada es exitosa y el cuerpo de la respuesta no es nulo, retorna `Resource.Success`.
     * Si la llamada es exitosa pero el cuerpo es nulo, retorna `Resource.Failure` con un mensaje indicando que el cuerpo está vacío.
     * Si la respuesta es un error HTTP, parsea el mensaje de error usando `ApiError.parserHttpError` y retorna `Resource.Failure`.
     * Captura excepciones comunes como `HttpException`, `IOException`, y `ApiError.JsonError` transformándolas en estados de fallo adecuados.
     * Otras excepciones no especificadas también se capturan y se reportan como errores desconocidos.
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("SafeApiCall", "Iniciando la llamada a la API")
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("SafeApiCall", "Llamada a la Api exitosa")
                        Resource.Success(it)
                    } ?: run {
                        Log.e("SafeApiCall", "Llamada exitosa a la Api, pero el cuerpo esta null")
                        Resource.Failure(true, response.code(), "Received null response body")
                    }
                } else {
                    val errorMessage = ApiError.parserHttpError(response)
                    Log.e(
                        "SafeApiCall",
                        "Fallo en la llamada a la API con error HTTP: "
                    )
                    Resource.Failure(false, response.code(), errorMessage)
                }
            } catch (throwable: Throwable) {
                Log.e(
                    "SafeApiCall",
                    "Fallo en la llamada a la Api con exception: ${throwable.message}"
                )
                when (throwable) {
                    is HttpException -> {
                        val errorMessage = ApiError.parserHttpError(throwable.response()!!)
                        Log.e(
                            "SafeApiCall",
                            "Fallo en la llamada a la Api con HttpException: "
                        )
                        Resource.Failure(false, throwable.code(), errorMessage)
                    }

                    is IOException -> {
                        Log.e(
                            "SafeApiCall",
                            "Fallo en la llamada a la Api con IOException: ${throwable.message}"
                        )
                        Resource.Failure(true, null, "Network error: ${throwable.message}")
                    }

                    is ApiError.JsonError -> { // Aquí manejamos el nuevo tipo de error
                        Log.e(
                            "SafeApiCall",
                            "Fallo de serialización del objeto: ${throwable.message}, Type: ${throwable.type}"
                        )
                        Resource.Failure(
                            true,
                            null,
                            "JSON error: ${throwable.message}, Type: ${throwable.type}"
                        )
                    }

                    else -> {
                        Log.e(
                            "SafeApiCall",
                            "Fallo en la llamada con error desconocido: ${throwable.message}"
                        )
                        Resource.Failure(true, null, "Unknown error: ${throwable.message}")
                    }
                }
            }
        }
    }
}