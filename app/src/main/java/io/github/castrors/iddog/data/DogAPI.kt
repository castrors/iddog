package io.github.castrors.iddog.data

import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DogAPI {

    @POST("/signup")
    fun signUp(@Query("email") email: String): Call<UserEntity>

    @GET("/feed")
    fun fetchDogs(@Query("category") category: String = ""): Call<DogContentEntity>
}
