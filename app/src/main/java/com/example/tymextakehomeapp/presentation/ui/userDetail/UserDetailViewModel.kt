package com.example.tymextakehomeapp.presentation.ui.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymextakehomeapp.domain.model.DTOUser
import com.example.tymextakehomeapp.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for loading and exposing the details of a GitHub user.
 *
 * This ViewModel interacts with the [GetUserDetailUseCase] to retrieve user details from
 * either the local Room database or the remote API if not already cached.
 *
 * @property getUserDetailUseCase The use case that provides user detail logic.
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
    private val _user = MutableStateFlow<DTOUser?>(null)
    val user: StateFlow<DTOUser?> = _user

    fun loadUserDetail(id: Int?) {
        viewModelScope.launch {
            val result = getUserDetailUseCase.getUserDetailById(id)
            _user.value = result
        }
    }
}

