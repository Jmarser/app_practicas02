package com.jmarser.app_practicas02.data.remote.network

import com.jmarser.app_practicas02.data.remote.modelsDTO.UserDto
import retrofit2.Response
import retrofit2.http.GET


/**
 * Project: MyTaxiClient
 * File: ApiService
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2024

 * Interfaz para definir los métodos de llamada de la API.
 *
 * Esta interfaz utiliza Retrofit para declarar métodos que corresponden a las operaciones de la API.
 * Cada método dentro de esta interfaz representa una posible llamada REST a la API, incluyendo
 * operaciones de tipo GET, POST, PUT, DELETE, entre otras. Retrofit convierte estas declaraciones
 * en llamadas HTTP, maneja la serialización y deserialización de datos y gestiona la conexión
 * con el servidor.
 *
 * Ejemplos de métodos incluidos en esta interfaz podrían ser `login`, `register`, `fetchUserDetails`,
 * `updateProfile`, `deleteAccount`, entre otros. Estos métodos deben ser anotados adecuadamente
 * con las anotaciones de Retrofit como @GET, @POST, @PUT, etc., para indicar el tipo de operación
 * HTTP y la ruta de acceso al recurso en el servidor.
 *
 * Ejemplo:
 * ```kotlin
 * @POST("/api/login")
 * fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
 *
 * @GET("/api/user/details")
 * fun fetchUserDetails(): Call<UserDetails>
 * ```
 *
 * Nota: Es esencial definir correctamente los parámetros de los métodos y los tipos de retorno
 * para asegurar que la comunicación con la API sea fluida y eficiente.
 */
interface ApiService {

    @GET(ConstNetwork.GET_USERS)
    suspend fun getAllUsers(): Response<List<UserDto>>
}