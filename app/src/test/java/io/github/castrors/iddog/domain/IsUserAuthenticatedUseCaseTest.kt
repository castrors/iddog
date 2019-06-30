package io.github.castrors.iddog.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.SessionGateway
import io.github.castrors.iddog.domain.base.None
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class IsUserAuthenticatedUseCaseTest{
    private lateinit var isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase
    private val sessionGateway: SessionGateway = mock()

    @Before
    fun setup() {
        isUserAuthenticatedUseCase = IsUserAuthenticatedUseCase(sessionGateway)
    }

    @Test
    fun givenUserAuthenticated_thenShouldBeTrue() {
        whenever(sessionGateway.isUserAuthenticated()).thenReturn(true)

        val result = isUserAuthenticatedUseCase.run(None)

        Assert.assertEquals(result, sessionGateway.isUserAuthenticated())

    }

    @Test
    fun givenUserNotAuthenticated_thenShouldBeBothFalse() {
        whenever(sessionGateway.isUserAuthenticated()).thenReturn(false)

        val result = isUserAuthenticatedUseCase.run(None)

        Assert.assertEquals(result, sessionGateway.isUserAuthenticated())
    }
}