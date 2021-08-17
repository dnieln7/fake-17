package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.data.network.user.UserService
import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.domain.UserCredentials
import io.reactivex.rxjava3.core.Single

/**
 * An implementation of [UserAuthSource] that uses Retrofit to authenticate users.
 *
 * @author dnieln7
 *
 * @param service A [UserService] implementation to interact with the server.
 */
class UserRemoteAuthSource(private val service: UserService) : UserAuthSource {

    override fun login(userCredentials: UserCredentials): Single<User> {
        return service.login(userCredentials)
    }

    override fun signUp(user: User): Single<User> {
        return service.signup(user)
    }
}