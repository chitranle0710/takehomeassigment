package com.example.tymextakehomeapp.data.repository

import androidx.paging.PagingData
import com.example.tymextakehomeapp.domain.model.DTOUser
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun fetchAndStoreUser(id: Int?)

    suspend fun getStoredUser(id: Int?): DTOUser?

    fun getPagedUsers(): Flow<PagingData<DTOUser>>
}