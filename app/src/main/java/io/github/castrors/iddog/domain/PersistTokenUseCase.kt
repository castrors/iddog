package io.github.castrors.iddog.domain

import io.github.castrors.iddog.data.SessionGateway
import io.github.castrors.iddog.domain.base.Interactor

class PersistTokenUseCase(
    private val sessionGateway: SessionGateway
) : Interactor<String, Boolean> {
    override fun run(params: String): Boolean {
        return sessionGateway.persistToken(params)
    }
}