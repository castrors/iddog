package io.github.castrors.iddog.data.model

import io.github.castrors.iddog.presentation.model.User

data class UserEntity(
    val _id: String,
    val email: String,
    val token: String
) {

    fun toUser() = User(_id, email, token)

    companion object {
        fun empty() = UserEntity("invalid_id", "invalid_email", "invalid_token")
    }
}