package io.github.castrors.iddog.data.base

sealed class Either<out L, out R> {
    data class Left<out L>(val error: L) : Either<L, Nothing>()
    data class Right<out R>(val content: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(error: L) = Left(error)
    fun <R> right(content: R) = Right(content)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(error)
            is Right -> fnR(content)
        }
}