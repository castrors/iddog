package io.github.castrors.iddog.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.DogsGateway
import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.data.model.UserEntity
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.ErrorState
import io.github.castrors.iddog.presentation.model.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SignUpUseCaseTest {
    private lateinit var signUpUseCase: SignUpUseCase
    private val dogsGateway: DogsGateway = mock()

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase(dogsGateway)
    }

    @Test
    fun givenGetDogs_whenInvokeGatewayWithSuccess_thenShouldMapDogsList() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = User("expected_id", "expected@email.com", "expected_token")
            whenever(dogsGateway.signUp(email)).thenReturn(
                Either.Right(
                    UserEntity("expected_id", "expected@email.com", "expected_token")
                )
            )

            val uiState = signUpUseCase.run(email)
            val contentState = uiState as ContentState

            assertEquals(contentState.content, expectedResult)
        }
    }

    @Test
    fun givenGetDogs_whenInvokeGatewayWithFailure_thenShouldReturnErrorState() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = Exception("Unmapped exception")
            whenever(dogsGateway.signUp(email)).thenReturn(Either.Left(expectedResult))

            val uiState = signUpUseCase.run(email)
            val errorState = uiState as ErrorState

            assertEquals(errorState.error, expectedResult)
        }

    }
}