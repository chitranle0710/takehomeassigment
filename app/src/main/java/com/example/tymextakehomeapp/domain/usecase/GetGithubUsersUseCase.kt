package com.example.tymextakehomeapp.domain.usecase

import androidx.paging.PagingData
import com.example.tymextakehomeapp.data.repository.IUserRepository
import com.example.tymextakehomeapp.domain.model.DTOUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGithubUsersUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(): Flow<PagingData<DTOUser>> {
        return repository.getPagedUsers()
    }
}