package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * An implementation of [UserDataSource] that uses in memory list to authenticate users.
 *
 * @author dnieln7
 */
class UserInMemoryDataSource : UserDataSource {
    private val users: MutableList<User> = mutableListOf()

    override fun save(user: User): Single<Long> {
        return Single.fromCallable {
            Thread.sleep(2000)
            users.add(user)

            return@fromCallable users.lastIndex.toLong()
        }
    }

    override fun getFirstUser(): Maybe<User> {
        return Maybe.fromCallable {
            Thread.sleep(2000)

            return@fromCallable users.first()
        }
    }

    override fun deleteAll(): Single<Int> {
        return Single.fromCallable {
            Thread.sleep(2000)

            val count = users.size

            users.clear()

            return@fromCallable count
        }
    }
}