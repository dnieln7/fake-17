package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.domain.UserCredentials
import io.reactivex.rxjava3.core.Single

/**
 * An implementation of [UserAuthSource] that uses in memory list to authenticate users.
 *
 * @author dnieln7
 */
class UserInMemoryAuthSource : UserAuthSource {
    private val users: MutableList<User> = mutableListOf(
        User(
            1,
            "Daniel",
            "Nolasco",
            "dnieln7@gmail.com",
            "admin",
            emptyList(),
            "estudiante"
        )
    )

    override fun login(userCredentials: UserCredentials): Single<User> {
        return Single.fromCallable {
            Thread.sleep(2000)

            val user = users.find { it.email == userCredentials.email }

            if (user == null) {
                throw Exception("User not found")
            } else {
                if (user.password == userCredentials.password) {
                    return@fromCallable user
                } else {
                    throw Exception("Wrong password")
                }
            }
        }
    }

    override fun signUp(user: User): Single<User> {
        return Single.fromCallable {
            Thread.sleep(2000)

            val existing = users.find { it.email == user.email }

            if (existing != null) {
                throw Exception("Email already exits")
            }

            users.add(user)

            return@fromCallable user
        }
    }
}