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

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val userDao: UserDAO
) : IUserRepository {
    override suspend fun fetchAndStoreUser(id: Int?) {
        if (id == null) return
        val user = apiService.getUserDetail(id)
        val userEntity = UserEntity(
            id = user.id,
            login = user.login,
            name = user.name,
            followers = user.followers,
            following = user.following,
            avatarUrl = user.avatarUrl,
            location = user.location
        )
        userDao.insertUsers(userEntity)
    }

    override suspend fun getStoredUser(id: Int?): DTOUser? {
        return id?.let {
            val userEntity = userDao.getUserById(it) ?: return null
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