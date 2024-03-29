package io.github.castrors.iddog.domain

import io.github.castrors.iddog.data.DogsGateway
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.ErrorState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GetDogsUseCase(
    private val dogsGateway: DogsGateway
) : SuspendedInteractor<String, UIState> {
    override suspend fun run(params: String): UIState {
        return withContext(IO) {
            val result = dogsGateway.fetchDogs(params)
            result.either(
                { error -> ErrorState(error) },
                { dogContentEntity -> ContentState(dogContentEntity.list.map { Dog(it) }) }
            ) as UIState
        }
    }
}