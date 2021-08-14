package com.dnieln7.fake17

import android.content.Context
import com.dnieln7.fake17.data.source.user.UserInMemoryAuthSource
import com.dnieln7.fake17.data.source.user.UserInMemoryDataSource

class ServiceLocator(private val context: Context) {

    val userAuthSource = UserInMemoryAuthSource()
    val userDataSource = UserInMemoryDataSource()
}
