package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun save(user: User): Single<Long>

    fun getFirstUser(): Maybe<User>

    fun deleteAll(): Single<Int>
}