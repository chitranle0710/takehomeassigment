package com.example.tymextakehomeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.tymextakehomeapp.data.datasource.UserPagingSource
import com.example.tymextakehomeapp.data.local.dao.UserDAO
import com.example.tymextakehomeapp.data.local.entity.UserEntity
import com.example.tymextakehomeapp.data.remote.ApiService
import com.example.tymextakehomeapp.domain.model.DTOUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [IUserRepository] responsible for handling user data operations.
 *
 * This repository interacts with both the remote API and the local Room database.
 * It provides paged user listing and detailed user fetching with local caching.
 *
 * @property apiService The API service used to fetch data from GitHub.
 * @property userDao The DAO interface to access Room database for user entity.
 */

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val userDao: UserDAO
) : IUserRepository {

    /**
     * Retrieves a stored user detail from the local database.
     * If the user is not found locally, it fetches from the API, stores it, and returns it.
     *
     * @param id The ID of the user to fetch.
     * @return The user details as a [DTOUser], or null if the ID is null.
     */

    override suspend fun getStoredUser(id: Int?): DTOUser? {
        if (id == null) return null

        val userEntity = userDao.getUserById(id)

        return if (userEntity != null) {
            DTOUser(
                id = userEntity.id,
                login = userEntity.login,
                name = userEntity.name,
                followers = userEntity.followers,
                following = userEntity.following,
                avatarUrl = userEntity.avatarUrl,
                location = userEntity.location,
                htmlUrl = userEntity.avatarUrl
            )
        } else {
            val user = apiService.getUserDetail(id)
            val newUserEntity = UserEntity(
                id = user.id,
                login = user.login,
                name = user.name,
                followers = user.followers,
                following = user.following,
                avatarUrl = user.avatarUrl,
                location = user.location
            )
            userDao.insertUsers(newUserEntity)

            DTOUser(
                id = user.id,
                login = user.login,
                name = user.name,
                followers = user.followers,
                following = user.following,
                avatarUrl = user.avatarUrl,
                location = user.location,
                htmlUrl = user.avatarUrl
            )
        }
    }

    override fun getPagedUsers(): Flow<PagingData<DTOUser>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
            .map { pagingData ->
                pagingData.map { user ->
                    DTOUser(
                        id = user.id,
                        login = user.login,
                        avatarUrl = user.avatarUrl,
                        name = null,
                        followers = null,
                        following = null,
                        location = null,
                        htmlUrl = null
                    )
                }
            }
    }
}