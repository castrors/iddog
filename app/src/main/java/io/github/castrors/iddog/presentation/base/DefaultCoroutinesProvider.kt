package io.github.castrors.iddog.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultBuilderProvider : CoroutinesBuilderProvider {

    override fun launch(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit) {
        scope.launch(block = block)
    }
}