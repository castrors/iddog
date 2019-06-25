package io.github.castrors.iddog.data.model

data class UserEntity(
    val _id: String,
    val email: String,
    val token: String
) {
    companion object {
        fun empty() = UserEntity("invalid_id", "invalid_email", "invalid_token")
    }
}