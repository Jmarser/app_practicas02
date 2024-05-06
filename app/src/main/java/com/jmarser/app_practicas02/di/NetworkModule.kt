package com.jmarser.app_practicas02.di


import com.jmarser.app_practicas02.data.remote.network.ApiService
import com.jmarser.app_practicas02.data.remote.network.ConstNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Project: App_Practicas02
 * File: NetworkModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024

 * `NetworkModule` es un módulo de Dagger-Hilt que proporciona las dependencias relacionadas con la red para toda la aplicación.
 * Este módulo asegura que instancias críticas para las operaciones de red, como `OkHttpClient`, `Retrofit`, y `ApiService`, sean creadas y gestionadas eficientemente.
 * La anotación `@Module` indica que esta clase provee dependencias, mientras que `@InstallIn(SingletonComponent::class)` determina que las dependencias proporcionadas tienen un ciclo de vida de Singleton, existiendo una única instancia a lo largo de toda la aplicación.
 *
 * Funciones proveedoras:
 * - `provideOkHttpClient`: Configura y provee una instancia de `OkHttpClient` con interceptores para la depuración.
 * - `provideRetrofit`: Crea y provee una instancia de `Retrofit` configurada para la comunicación con la API, utilizando el `OkHttpClient` proporcionado.
 * - `provideApiService`: Provee una instancia de `ApiService`, generada por Retrofit, para realizar llamadas a la API.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provee una instancia de `OkHttpClient` configurada con un `HttpLoggingInterceptor` para la depuración de solicitudes y respuestas.
     * Los tiempos de espera para la conexión, lectura, y ejecución de llamadas están establecidos en 30 segundos.
     *
     * @return Una instancia de `OkHttpClient`.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            //.addInterceptor(ApiKeyInterceoptor()) Si necesitamos añadir una apiKey a la llamada.
            .build()
    }

    /**
     * Crea y provee una instancia de `Retrofit` configurada con la base URL para las solicitudes de red, utilizando un `OkHttpClient` para la comunicación de red y un `GsonConverterFactory` para la serialización de JSON.
     *
     * @param okHttpClient La instancia de `OkHttpClient` a utilizar por Retrofit.
     * @return Una instancia de `Retrofit`.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(ConstNetwork.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provee una instancia de `ApiService`, la interfaz generada por Retrofit que define los endpoints de la API.
     * Utiliza la instancia de `Retrofit` para crear la implementación de la interfaz.
     *
     * @param retrofit La instancia de `Retrofit` utilizada para crear `ApiService`.
     * @return Una instancia de `ApiService`.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}