package com.ryben.stackexchange.data

import com.ryben.stackexchange.data.model.UserResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException

class UserRepositoryImplTest {

    private val apiService: ApiService = mockk()

    private lateinit var repository: UserRepositoryImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
         val testDispatcher = UnconfinedTestDispatcher()

        repository = UserRepositoryImpl(
            ioDispatcher = testDispatcher,
            apiService = apiService
        )
    }

    @Test
    fun `searchUserByName success on API call success`() = runTest {
        // Given
        val mockDto = UserResponseDto(items = listOf(mockk(relaxed = true)))
        coEvery {
            apiService.getUsers(any(), any(), any(), any(), any())
        } returns mockDto

        // When
        val result = repository.searchUserByName("John")

        // Then
        assert(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify(exactly = 1) { apiService.getUsers("John", 20, "asc", "name", "stackoverflow") }
    }

    @Test
    fun `searchUserByName fail on API exception`() = runTest {
        // Given
        val exception = RuntimeException("Network Error")
        coEvery { apiService.getUsers(any(), any(), any(), any(), any()) } throws exception

        // When
        val result = repository.searchUserByName("John")

        // Then
        assert(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test(expected = CancellationException::class)
    fun `searchUserByName rethrows CancellationException`() = runTest {
        // Given
        coEvery {
            apiService.getUsers(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } throws CancellationException()

        // When
        repository.searchUserByName("John")
    }
}