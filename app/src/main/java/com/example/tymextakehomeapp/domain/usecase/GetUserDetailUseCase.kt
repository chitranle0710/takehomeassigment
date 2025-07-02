package com.example.tymextakehomeapp.domain.usecase

import com.example.tymextakehomeapp.data.repository.IUserRepository
import com.example.tymextakehomeapp.domain.model.DTOUser
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    suspend fun getUserDetailById(id: Int?): DTOUser? {
        return repository.getStoredUser(id)
    }
}