package io.github.castrors.iddog.domain.base

interface SuspendedInteractor<in Params, out Out> {
    suspend fun run(params: Params): Out
}