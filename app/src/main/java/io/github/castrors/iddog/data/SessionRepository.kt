package io.github.castrors.iddog.data

import com.orhanobut.hawk.Hawk

object SessionRepository {
    private const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
    var token: String = Hawk.get(USER_TOKEN_KEY, "")
}