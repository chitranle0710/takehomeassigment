package com.example.tymextakehomeapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("html_url")
    val htmlUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("followers")
    val followers: Int? = null,

    @SerializedName("following")
    val following: Int? = null,
)