package com.dnieln7.fake17.data.source.user

import com.dnieln7.fake17.domain.User
import kotlinx.coroutines.delay

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

    override suspend fun login(email: String, password: String): User {
        val user = users.find { it.email == email }

        delay(2000)

        if (user == null) {
            throw Exception("User not found")
        } else {
            if (user.password == password) {
                return user
            } else {
                throw Exception("Wrong password")
            }
        }
    }

    override suspend fun signUp(user: User): User {
        val existing = users.find { it.email == user.email }

        delay(2000)

        if (existing != null) {
            throw Exception("Email already exits")
        }

        users.add(user)

        return user
    }
}