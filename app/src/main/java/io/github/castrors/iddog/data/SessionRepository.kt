package io.github.castrors.iddog.data

import com.orhanobut.hawk.Hawk

class SessionRepository: SessionGateway {

    private val USER_TOKEN_KEY = "USER_TOKEN_KEY"

    override fun isUserAuthenticated(): Boolean {
        return Hawk.get(USER_TOKEN_KEY, "").isNotEmpty()
    }

    override fun provideToken(): String {
        return Hawk.get(USER_TOKEN_KEY, "")
    }

    override fun persistToken(token: String) : Boolean {
        return Hawk.put(USER_TOKEN_KEY, token)
    }

    override fun signOut(): Boolean {
        return Hawk.delete(USER_TOKEN_KEY)
    }
}