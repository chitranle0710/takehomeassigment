package com.example.tymextakehomeapp.domain.usecase

import androidx.paging.PagingData
import com.example.tymextakehomeapp.data.repository.IUserRepository
import com.example.tymextakehomeapp.domain.model.DTOUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case responsible for retrieving a paginated list of GitHub users.
 *
 * This use case delegates to the [IUserRepository] to fetch users via remote PagingSource.
 *
 * @property repository The repository implementation used to access user data.
 */
class GetGithubUsersUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    /**
     * Invokes the use case to retrieve paged users.
     *
     * @return A [Flow] of [PagingData] containing [DTOUser] items.
     */
    operator fun invoke(): Flow<PagingData<DTOUser>> {
        return repository.getPagedUsers()
    }
}