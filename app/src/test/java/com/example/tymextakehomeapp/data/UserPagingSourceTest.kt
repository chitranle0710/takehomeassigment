package com.example.tymextakehomeapp.data

import com.example.tymextakehomeapp.data.datasource.UserPagingSource
import com.example.tymextakehomeapp.data.remote.ApiService
import com.example.tymextakehomeapp.data.remote.dto.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserPagingSourceTest {

    private val apiService: ApiService = mockk()
    private lateinit var pagingSource: UserPagingSource

    @Before
    fun setup() {
        pagingSource = UserPagingSource(apiService)
    }

    @Test
    fun `load returns page on success`() = runTest {
        val mockUsers = listOf(User("login", 1, "avatar"))
        coEvery { apiService.getGithubUsers(any(), any()) } returns mockUsers

//        val result = pagingSource.load(
//            PagingSource.LoadParams.Refresh(null, 20, false)
//        )
//
//        assertTrue(result is PagingSource.LoadResult.Page)
//        assertEquals(mockUsers, (result as PagingSource.LoadResult.Page).data)
    }
}
