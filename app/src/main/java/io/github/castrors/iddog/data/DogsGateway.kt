package io.github.castrors.iddog.data

import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.UserDTO
import io.github.castrors.iddog.data.model.UserEntity

typealias UserResponse = Either<Throwable, UserDTO>
typealias DogContentResponse = Either<Throwable, DogContentEntity>

interface DogsGateway {
    suspend fun signUp(email: String): UserResponse
    suspend fun fetchDogs(category: String = ""): DogContentResponse
}