package com.example.tymextakehomeapp.domain.model

data class DTOUser(
    val login: String?,
    val id: Int?,
    val avatarUrl: String?,
    val htmlUrl: String?,
    val name: String?,
    val location: String?,
    val followers: Int?,
    val following: Int?
)