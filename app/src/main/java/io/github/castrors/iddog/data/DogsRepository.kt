package io.github.castrors.iddog.data

import io.github.castrors.iddog.data.base.Requestable
import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.EmailDTO
import io.github.castrors.iddog.data.model.UserDTO
import io.github.castrors.iddog.data.model.UserEntity

class DogsRepository(
    private val remote: DogAPI
): Requestable(), DogsGateway {
    override suspend fun signUp(email: String): UserResponse {
        return request(remote.signUp(EmailDTO(email)), UserDTO(UserEntity.empty()))
    }

    override suspend fun fetchDogs(category: String): DogContentResponse {
        return request(remote.fetchDogs(category), DogContentEntity.empty())
    }

}