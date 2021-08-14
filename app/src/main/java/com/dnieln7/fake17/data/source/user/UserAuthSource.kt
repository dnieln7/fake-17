package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User

interface UserAuthSource {
    suspend fun login(email: String, password: String): User

    suspend fun signUp(user: User): User
}