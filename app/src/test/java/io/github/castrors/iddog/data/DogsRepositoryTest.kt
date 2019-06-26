package io.github.castrors.iddog.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.data.base.Either
import io.github.castrors.iddog.data.model.DogContentEntity
import io.github.castrors.iddog.data.model.EmailDTO
import io.github.castrors.iddog.data.model.UserDTO
import io.github.castrors.iddog.data.model.UserEntity
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import retrofit2.Response

class DogsRepositoryTest {

    private lateinit var dogsRepository: DogsRepository
    private val api: DogAPI = mock()

    @Before
    fun setup() {
        dogsRepository = DogsRepository(api)
    }

    @Test
    fun givenFetchDogs_whenResultIsSuccess_thenShouldReceiveDogContentEntity() {
        runBlocking {
            val expectedResult = DogContentEntity("husky", listOf("url"))
            whenever(api.fetchDogs()).thenReturn(Response.success(expectedResult))

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenFetchDogs_whenResultIsError_thenShouldReceiveDogContentEntityEmpty() {
        runBlocking {
            val expectedResult = DogContentEntity.empty()
            whenever(api.fetchDogs()).thenReturn(Response.success(null))

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenFetchDogs_whenResultIs500_thenShouldThrowException() {
        runBlocking {
            val expectedResult = Exception("Unmapped exception")
            whenever(api.fetchDogs()).thenReturn(Response.error(500, ResponseBody.create(MediaType.parse("text"), "")))

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

    @Ignore
    @Test
    fun givenFetchDogs_whenUnexpectedError_thenShouldThrowException() {
        runBlocking {
            val expectedResult = Exception("Unmapped exception")
            whenever(api.fetchDogs()).thenAnswer { throw expectedResult }

            val eitherResponse = dogsRepository.fetchDogs()

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

    @Test
    fun givenSignUp_whenResultIsSuccess_thenShouldReceiveUserEntity() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = UserDTO(UserEntity("expected_id", "expected@email.com", "expected_token"))
            whenever(api.signUp(EmailDTO(email))).thenReturn(Response.success(expectedResult))

            val eitherResponse = dogsRepository.signUp(email)

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenSignUp_whenResultIsError_thenShouldReceiveUserEntityEmpty() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = UserDTO(UserEntity.empty())
            whenever(api.signUp(EmailDTO(email))).thenReturn(Response.success(null))

            val eitherResponse = dogsRepository.signUp(email)

            val response = (eitherResponse as Either.Right).content
            assertEquals(response, expectedResult)
        }
    }

    @Test
    fun givenSignUp_whenResultIs500_thenShouldThrowException() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = Exception("Unmapped exception")
            whenever(api.signUp(EmailDTO(email))).thenReturn(
                Response.error(
                    500,
                    ResponseBody.create(MediaType.parse("text"), "")
                )
            )

            val eitherResponse = dogsRepository.signUp(email)

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

    @Ignore
    @Test
    fun givenSignUp_whenUnexpectedError_thenShouldThrowException() {
        runBlocking {
            val email = "expected@email.com"
            val expectedResult = Exception("Unmapped exception")
            whenever(api.signUp(EmailDTO(email))).thenAnswer { throw expectedResult }

            val eitherResponse = dogsRepository.signUp(email)

            val response = (eitherResponse as Either.Left).error
            assertEquals(response.message, expectedResult.message)
        }
    }

}