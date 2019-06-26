package io.github.castrors.iddog.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutineAwareViewModel(coroutineContext: CoroutineContext) : ViewModel() {

    private val job = Job()
    protected val main = CoroutineScope(coroutineContext + job)

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}

