package com.dnieln7.fake17.domain

import com.google.gson.annotations.SerializedName

data class UserCredentials(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rol")
    val role: String
)