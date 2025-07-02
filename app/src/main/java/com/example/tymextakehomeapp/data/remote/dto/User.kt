package com.example.tymextakehomeapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,
)