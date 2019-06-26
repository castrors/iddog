package io.github.castrors.iddog.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain.request().newBuilder()) {
        val token: String = SessionRepository.token

        if (token.isNotEmpty())
            addHeader("authorization", token)

        chain.proceed(build())
    }

}
