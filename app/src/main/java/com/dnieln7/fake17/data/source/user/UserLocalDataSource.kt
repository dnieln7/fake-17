package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.data.database.dao.UserDao
import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

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