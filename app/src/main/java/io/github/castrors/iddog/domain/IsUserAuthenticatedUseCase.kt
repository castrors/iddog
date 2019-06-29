package io.github.castrors.iddog.domain

import io.github.castrors.iddog.data.SessionGateway
import io.github.castrors.iddog.domain.base.Interactor
import io.github.castrors.iddog.domain.base.None

class IsUserAuthenticatedUseCase(
    private val sessionGateway: SessionGateway
) : Interactor<None, Boolean> {
    override fun run(params: None): Boolean {
        return sessionGateway.isUserAuthenticated()
    }
}