package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.data.database.dao.UserDao
import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * An implementation of [UserDataSource] that uses in memory list to read and save user data.
 *
 * @author dnieln7
 *
 * @param dao A [UserDao] implementation to interact with the database.
 */
class UserLocalDataSource(private val dao: UserDao) : UserDataSource {
    override fun save(user: User): Single<Long> {
        return dao.save(user)
    }

    override fun getFirstUser(): Maybe<User> {
        return dao.getFirstUser()
    }

    override fun deleteAll(): Single<Int> {
        return dao.deleteAll()
    }
}