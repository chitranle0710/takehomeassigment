package com.example.tymextakehomeapp.presentation.ui.listUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tymextakehomeapp.domain.model.DTOUser
import com.example.tymextakehomeapp.domain.usecase.GetGithubUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val getGithubUsersUseCase: GetGithubUsersUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<PagingData<DTOUser>>(PagingData.empty())
    val users: StateFlow<PagingData<DTOUser>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            getGithubUsersUseCase()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _users.value = pagingData
                }
        }
    }
}