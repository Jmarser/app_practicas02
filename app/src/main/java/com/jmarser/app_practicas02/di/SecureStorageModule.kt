package com.jmarser.app_practicas02.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.jmarser.app_practicas02.presentation.core.AppPreferences
import com.jmarser.app_practicas02.presentation.core.ConstantsUtils
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

 * Módulo de Dagger para proporcionar dependencias relacionadas con el almacenamiento seguro.
 *
 * Este módulo ofrece dos proveedores principales:
 *
 * 1. {@code provideEncryptedSharedPreferences}: Proporciona una instancia de {@link SharedPreferences}
 *    que utiliza almacenamiento cifrado para asegurar los datos de preferencias. Utiliza
 *    {@link MasterKeys} para generar una clave maestra con especificaciones AES256_GCM_SPEC y
 *    crea un archivo de preferencias cifrado nombrado según {@link ConstantsUtils.NAME_FILE_PREFERENCES}.
 *    Este almacenamiento es particularmente útil para guardar datos sensibles que necesitan
 *    un nivel adicional de seguridad.
 *
 * 2. {@code provideAppPreferences}: Proporciona una instancia de {@link AppPreferences},
 *    una clase de abstracción sobre {@link SharedPreferences} para facilitar el trabajo con
 *    preferencias de la aplicación. Esta abstracción puede ayudar a manejar las preferencias
 *    de manera más estructurada y coherente a través de la aplicación.
 *
 * Ambos métodos están anotados con {@link Singleton} para asegurar una única instancia de cada
 * proveedor a través de la vida de la aplicación, garantizando así la coherencia de los datos
 * almacenados.
 *
 * Este módulo debe ser instalado en {@link SingletonComponent} para asegurar que las dependencias
 * proporcionadas estén disponibles en el alcance global de la aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
object SecureStorageModule {

    /**
     * Proporciona una instancia segura de {@link SharedPreferences} usando almacenamiento cifrado.
     *
     * @param context Contexto de la aplicación, necesario para la creación de las preferencias cifradas.
     * @return Una instancia de {@link SharedPreferences} cifrada con AES256.
     */
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {

        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            ConstantsUtils.NAME_FILE_PREFERENCES,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /**
     * Proporciona una instancia de {@link AppPreferences}, facilitando el manejo de preferencias
     * de la aplicación a través de una interfaz más amigable.
     *
     * @param sharedPreferences Instancia de {@link SharedPreferences} a envolver.
     * @return Una instancia de {@link AppPreferences} que utiliza las preferencias proporcionadas.
     */
    @Provides
    @Singleton
    fun provideAppPreferences(sharedPreferences: SharedPreferences): AppPreferences {
        return AppPreferences(sharedPreferences)
    }
}