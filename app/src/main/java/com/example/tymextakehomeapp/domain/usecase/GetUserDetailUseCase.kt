package com.example.tymextakehomeapp.domain.usecase

import com.example.tymextakehomeapp.data.repository.IUserRepository
import com.example.tymextakehomeapp.domain.model.DTOUser
import javax.inject.Inject

/**
 * Use case for retrieving a single user's detailed information.
 *
 * It first attempts to retrieve the user from local Room storage. If not found,
 * it fetches from the remote API and stores it locally.
 *
 * @property repository The user repository that provides user data operations.
 */
class GetUserDetailUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    /**
     * Retrieves detailed information about a specific user.
     *
     * @param id The ID of the user.
     * @return A [DTOUser] object containing detailed user information, or null if ID is null.
     */
    suspend fun getUserDetailById(id: Int?): DTOUser? {
        return repository.getStoredUser(id)
    }
}