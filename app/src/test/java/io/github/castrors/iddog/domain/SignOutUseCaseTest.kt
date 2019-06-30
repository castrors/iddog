package io.github.castrors.iddog.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.SessionGateway
import io.github.castrors.iddog.domain.base.None
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SignOutUseCaseTest{
    private lateinit var signOutUseCase: SignOutUseCase
    private val sessionGateway: SessionGateway = mock()

    @Before
    fun setup() {
        signOutUseCase = SignOutUseCase(sessionGateway)
    }

    @Test
    fun givenSignOut_whenInvokeGatewayWithSuccess_thenShouldReturnTrue() {

        val expectedResult = true
        whenever(sessionGateway.signOut()).thenReturn(true)

        val result = signOutUseCase.run(None)

        Assert.assertEquals(result, expectedResult)

    }

    @Test
    fun givenSignOut_whenInvokeGatewayWithFailure_thenShouldReturnTrue() {
        val expectedResult = false
        whenever(sessionGateway.signOut()).thenReturn(false)

        val result = signOutUseCase.run(None)

        Assert.assertEquals(result, expectedResult)
    }
}