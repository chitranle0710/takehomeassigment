package com.example.tymextakehomeapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tymextakehomeapp.data.remote.ApiService
import com.example.tymextakehomeapp.data.remote.dto.User

class UserPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val since = params.key ?: 0
        return try {
            val response = apiService.getGithubUsers(since, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = response.lastOrNull()?.id
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? = null
}
