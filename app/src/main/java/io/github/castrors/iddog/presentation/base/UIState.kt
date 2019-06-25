package io.github.castrors.iddog.presentation.base

sealed class UIState

data class ErrorState(val error: Throwable) : UIState()
data class ContentState(val content: Any) : UIState()
object LoadingState : UIState()