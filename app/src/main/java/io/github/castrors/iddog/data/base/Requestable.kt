package io.github.castrors.iddog.data.base

import retrofit2.Response

abstract class Requestable {

    @Throws(Exception::class)
    protected fun <T> request(
        response: Response<T>,
        default: T
    ): Either<Throwable, T> {
        return try {
            when (response.isSuccessful) {
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(Exception("Unmapped exception"))
            }
        } catch (exception: Exception) {
            Either.Left(exception)
        }
    }
}