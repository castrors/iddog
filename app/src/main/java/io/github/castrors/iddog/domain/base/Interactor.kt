package io.github.castrors.iddog.domain.base

interface Interactor<in Params, out Out> {
    fun run(params: Params): Out
}