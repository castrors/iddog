package io.github.castrors.iddog.data.base

import retrofit2.Call

abstract class Requestable {

    @Throws(Exception::class)
    protected fun <T> request(
        call: Call<T>,
        default: T
    ): Either<Throwable, T> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(Exception("Unmapped exception"))
            }
        } catch (exception: Exception) {
            Either.Left(exception)
        }
    }

    @Throws(Exception::class)
    protected fun <T> request(
        call: Call<T>
    ): Either<Throwable, T?> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(response.body())
                false -> Either.Left(Exception("Unmapped exception"))
            }
        } catch (exception: Exception) {
            Either.Left(exception)
        }
    }
}