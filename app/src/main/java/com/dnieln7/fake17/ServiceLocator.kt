package com.dnieln7.fake17

import android.content.Context
import com.dnieln7.fake17.data.network.user.UserApi
import com.dnieln7.fake17.data.source.user.UserInMemoryDataSource
import com.dnieln7.fake17.data.source.user.UserRemoteAuthSource

class ServiceLocator(private val context: Context) {

    private val userApi = UserApi("https://eventos-uv-api.herokuapp.com")

    val userAuthSource = UserRemoteAuthSource(userApi.service)
    val userDataSource = UserInMemoryDataSource()
}
