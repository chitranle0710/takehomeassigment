package com.example.tymextakehomeapp.data

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.tymextakehomeapp.MainDispatcherRule
import com.example.tymextakehomeapp.domain.usecase.GetGithubUsersUseCase
import com.example.tymextakehomeapp.presentation.ui.listUser.ListUserViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListUserViewModelTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private val getUsersUseCase: GetGithubUsersUseCase = mockk()
    private lateinit var viewModel: ListUserViewModel

    @Before
    fun setup() {
        every { getUsersUseCase() } returns flowOf(PagingData.empty())
        viewModel = ListUserViewModel(getUsersUseCase)
    }

    @Test
    fun `users flow emits paging data`() = runTest {
        viewModel.users.test {
            val item = awaitItem()
            assertNotNull(item)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
