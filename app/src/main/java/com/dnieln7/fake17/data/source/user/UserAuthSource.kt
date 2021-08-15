package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.domain.UserCredentials
import io.reactivex.rxjava3.core.Single

interface UserAuthSource {
    fun login(userCredentials: UserCredentials): Single<User>

    fun signUp(user: User): Single<User>
}