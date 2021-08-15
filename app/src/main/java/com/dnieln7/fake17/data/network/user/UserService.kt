package com.dnieln7.fake17.data.network.user

import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.domain.UserCredentials
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("login")
    fun login(@Body userCredentials: UserCredentials): Single<User>

    @POST("usuarios")
    fun signup(@Body user: User): Single<User>
}