package com.dnieln7.fake17

import android.content.Context
import com.dnieln7.fake17.data.database.FakeDatabase.Companion.createFakeDatabase
import com.dnieln7.fake17.data.network.user.UserApi
import com.dnieln7.fake17.data.source.user.UserLocalDataSource
import com.dnieln7.fake17.data.source.user.UserRemoteAuthSource

class ServiceLocator(context: Context) {

    private val userApi = UserApi("https://eventos-uv-api.herokuapp.com")
    private val fakeDatabase = context.createFakeDatabase()

    val userAuthSource = UserRemoteAuthSource(userApi.service)
    val userDataSource = UserLocalDataSource(fakeDatabase.userDao())
}
