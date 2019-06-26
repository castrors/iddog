package io.github.castrors.iddog.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.DogsGateway
import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.DogEntity
import io.github.castrors.iddog.presentation.model.Dog
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.ErrorState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetDogsUseCaseTest {

    private lateinit var getDogsUseCase: GetDogsUseCase
    private val dogsGateway: DogsGateway = mock()

    @Before
    fun setup() {
        getDogsUseCase = GetDogsUseCase(dogsGateway)
    }

    @Test
    fun givenGetDogs_whenInvokeGatewayWithSuccess_thenShouldMapDogsList(){
        runBlocking {
            val category = "husky"
            val expectedResult = listOf(Dog("url"))
            whenever(dogsGateway.fetchDogs(category)).thenReturn(Either.Right(DogContentEntity("husky", listOf(DogEntity("url")))))

            val uiState = getDogsUseCase.run(category)
            val contentState = uiState as ContentState

            Assert.assertEquals(contentState.content, expectedResult)
        }
    }

    @Test
    fun givenGetDogs_whenInvokeGatewayWithFailure_thenShouldReturnErrorState(){
        runBlocking {
            val category = "husky"
            val expectedResult = Exception("Unmapped exception")
            whenever(dogsGateway.fetchDogs(category)).thenReturn(Either.Left(expectedResult))

            val uiState = getDogsUseCase.run(category)
            val errorState = uiState as ErrorState

            Assert.assertEquals(errorState.error, expectedResult)
        }

    }
}