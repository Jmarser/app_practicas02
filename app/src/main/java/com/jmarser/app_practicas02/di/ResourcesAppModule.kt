package com.jmarser.app_practicas02.di

import android.content.Context
import com.jmarser.app_practicas02.presentation.core.ResourceProvider
import com.jmarser.app_practicas02.presentation.core.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Project: App_Practicas02
 * File: NetworkModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024

 * `ResourcesAppModule` es un módulo de Dagger-Hilt diseñado para proveer dependencias relacionadas con los recursos de la aplicación.
 * Al instalar este módulo en el `SingletonComponent`, garantiza que la instancia de `ResourceProvider` sea accesible globalmente con un ciclo de vida de Singleton.
 *
 * Funciones proveedoras:
 * - `provideResourceProvider`: Provee una implementación de `ResourceProvider`, permitiendo el acceso a recursos de la aplicación como cadenas de texto y otros tipos de recursos.
 */
@Module
@InstallIn(SingletonComponent::class)
object ResourcesAppModule {

    /**
     * Provee una instancia de `ResourceProvider`, específicamente `ResourceProviderImpl`, que permite acceder a los recursos de la aplicación.
     * Utiliza el contexto de la aplicación para acceder a estos recursos, asegurando que no se mantenga una referencia al contexto de una actividad y evitando posibles fugas de memoria.
     *
     * @param context El contexto de la aplicación proporcionado por Dagger-Hilt.
     * @return Una instancia de `ResourceProvider`.
     */
    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }
}