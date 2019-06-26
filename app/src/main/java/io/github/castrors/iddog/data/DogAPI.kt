package io.github.castrors.iddog.data

import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.EmailDTO
import io.github.castrors.iddog.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DogAPI {

    @POST("/signup")
    suspend fun signUp(@Body email: EmailDTO): Response<UserDTO>

    @GET("/feed")
    suspend fun fetchDogs(@Query("category") category: String = ""): Response<DogContentEntity>
}
