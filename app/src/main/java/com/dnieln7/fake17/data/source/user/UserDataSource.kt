package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User

interface UserDataSource {
    suspend fun save(user: User): Long

    suspend fun deleteAll()
}