package io.github.castrors.iddog.data

interface SessionGateway {
    fun isUserAuthenticated() : Boolean
    fun provideToken() : String
    fun persistToken(token: String): Boolean
    fun signOut(): Boolean
}
