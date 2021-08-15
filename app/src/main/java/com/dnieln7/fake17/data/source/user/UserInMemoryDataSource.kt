package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Single

class UserInMemoryDataSource : UserDataSource {
    private val users: MutableList<User> = mutableListOf()

    override fun save(user: User): Single<Long> {
        return Single.fromCallable {
            Thread.sleep(2000)
            users.add(user)

            return@fromCallable users.lastIndex.toLong()
        }
    }

    override fun deleteAll() {
        users.clear()
    }
}