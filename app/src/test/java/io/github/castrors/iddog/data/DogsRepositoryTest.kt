package io.github.castrors.iddog.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.DogEntity
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class DogsRepositoryTest {

    lateinit var dogsRepository: DogsRepository
    private val api: DogAPI = mock()
    private val fetchDogCall: Call<DogContentEntity> = mock()

    @Before
    fun setup() {
        dogsRepository = DogsRepository(api)
    }

    @Test
    fun givenFetchDogs_whenResultIsSuccess_thenShouldReceiveDogContentEntity() {
        runBlocking {
            val expectedResult = DogContentEntity("husky", listOf(DogEntity("url")))
            whenever(fetchDogCall.execute()).thenReturn(Response.success(expectedResult))
            whenever(api.fetchDogs()).thenReturn(fetchDogCall)

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenFetchDogs_whenResultIsError_thenShouldReceiveDogContentEntityEmpty() {
        runBlocking {
            val expectedResult = DogContentEntity.empty()
            whenever(fetchDogCall.execute()).thenReturn(Response.success(null))
            whenever(api.fetchDogs()).thenReturn(fetchDogCall)

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenFetchDogs_whenResultIs500_thenShouldThrowException() {
        runBlocking {
            val expectedResult = Exception("Unmapped exception")
            whenever(fetchDogCall.execute()).thenReturn(Response.error(500, ResponseBody.create(MediaType.parse("text"), "")))
            whenever(api.fetchDogs()).thenReturn(fetchDogCall)

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

    @Test
    fun givenFetchDogs_whenUnexpectedError_thenShouldThrowException() {
        runBlocking {
            val expectedResult = Exception("Unmapped exception")
            whenever(fetchDogCall.execute()).thenAnswer { throw expectedResult }
            whenever(api.fetchDogs()).thenReturn(fetchDogCall)

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

}