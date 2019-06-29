package io.github.castrors.iddog.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import timber.log.Timber

private lateinit var okHttpClient: OkHttpClient

fun buildClient(
    sessionGateway: SessionGateway
): OkHttpClient {
    return if (::okHttpClient.isInitialized.not()) {
        val auth = AuthInterceptor(sessionGateway)
        val logging = HttpLoggingInterceptor { Timber.i(it) }.setLevel(Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(auth)
            .addInterceptor(logging)
            .build()

        okHttpClient
    } else {
        okHttpClient
    }
}