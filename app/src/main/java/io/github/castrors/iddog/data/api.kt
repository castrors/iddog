package io.github.castrors.iddog.data

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import timber.log.Timber

internal const val cacheSize: Long = 10 * 1024 * 1024 // 10MB
private lateinit var okHttpClient: OkHttpClient

fun buildClient(
    application: Application,
    sessionGateway: SessionGateway
): OkHttpClient {
    return if (::okHttpClient.isInitialized.not()) {
        val auth = AuthInterceptor(sessionGateway)
        val logging = HttpLoggingInterceptor { Timber.i(it) }.setLevel(Level.BODY)
        val cache = Cache(application.cacheDir, cacheSize)

        okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(auth)
            .addInterceptor(logging)
            .build()

        okHttpClient
    } else {
        okHttpClient
    }
}