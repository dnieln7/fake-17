package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User

class UserInMemoryDataSource : UserDataSource {
    private val users: MutableList<User> = mutableListOf()

    override suspend fun save(user: User): Long {
        users.add(user)

        return users.lastIndex.toLong()
    }

    override suspend fun deleteAll() {
        users.clear()
    }
}