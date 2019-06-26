package io.github.castrors.iddog.domain

import io.github.castrors.iddog.data.DogsGateway
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.ErrorState
import io.github.castrors.iddog.presentation.base.UIState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class SignUpUseCase(
    private val dogsGateway: DogsGateway
) : SuspendedInteractor<String, UIState> {
    override suspend fun run(params: String): UIState {
        return withContext(IO) {
            val result = dogsGateway.signUp(params)
            result.either(
                { error -> ErrorState(error) },
                { userEntity -> ContentState(userEntity.toUser()) }
            ) as UIState
        }
    }
}