package io.github.castrors.iddog.presentation.base

import kotlinx.coroutines.CoroutineScope

interface CoroutinesBuilderProvider {
    fun launch(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit)
}
