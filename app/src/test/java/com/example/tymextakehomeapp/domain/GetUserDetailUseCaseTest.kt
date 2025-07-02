package com.example.tymextakehomeapp.domain

import com.example.tymextakehomeapp.data.repository.IUserRepository
import com.example.tymextakehomeapp.domain.model.DTOUser
import com.example.tymextakehomeapp.domain.usecase.GetUserDetailUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUserDetailUseCaseTest {

    private lateinit var repository: IUserRepository
    private lateinit var useCase: GetUserDetailUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetUserDetailUseCase(repository)
    }

    @Test
    fun `should return user detail when id exists`() = runTest {
        // Given
        val dtoUser = DTOUser(
            id = 1,
            login = "simon",
            name = "The Simon",
            followers = 100,
            following = 50,
            avatarUrl = "https://github.com/images/error/simon.gif",
            location = "GitHub",
            htmlUrl = "https://github.com/simon"
        )

        coEvery { repository.getStoredUser(1) } returns dtoUser
        val result = useCase.getUserDetailById(1)
        assertEquals(dtoUser, result)
        coVerify { repository.getStoredUser(1) }
    }

    @Test
    fun `returns null when id is null`() = runTest {
        coEvery { repository.getStoredUser(null) } returns null

        val result = useCase.getUserDetailById(null)

        assertNull(result)
        coVerify { repository.getStoredUser(null) }
    }
}
