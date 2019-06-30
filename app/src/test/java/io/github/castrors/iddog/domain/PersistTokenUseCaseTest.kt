package io.github.castrors.iddog.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.SessionGateway
import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.presentation.base.ErrorState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PersistTokenUseCaseTest {
    private lateinit var persistTokenUseCase: PersistTokenUseCase
    private val sessionGateway: SessionGateway = mock()

    @Before
    fun setup() {
        persistTokenUseCase = PersistTokenUseCase(sessionGateway)
    }

    @Test
    fun givenPersistToken_whenInvokeGatewayWithSuccess_thenShouldMapDogsList() {

        val token = "token"
        val expectedResult = true
        whenever(sessionGateway.persistToken(token)).thenReturn(true)

        val result = persistTokenUseCase.run(token)

        Assert.assertEquals(result, expectedResult)

    }

    @Test
    fun givenPersistToken_whenInvokeGatewayWithFailure_thenShouldReturnErrorState() {
        val token = "token"
        val expectedResult = false
        whenever(sessionGateway.persistToken(token)).thenReturn(false)

        val result = persistTokenUseCase.run(token)

        Assert.assertEquals(result, expectedResult)
    }
}




