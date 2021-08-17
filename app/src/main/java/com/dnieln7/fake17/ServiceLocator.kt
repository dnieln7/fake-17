package com.dnieln7.fake17

import android.content.Context
import com.dnieln7.fake17.data.database.FakeDatabase.Companion.createFakeDatabase
import com.dnieln7.fake17.data.network.Api
import com.dnieln7.fake17.data.repository.CatRepository
import com.dnieln7.fake17.data.source.cat.CatApiDataSource
import com.dnieln7.fake17.data.source.cat.CatDbDataSource
import com.dnieln7.fake17.data.source.user.UserLocalDataSource
import com.dnieln7.fake17.data.source.user.UserRemoteAuthSource

/**
 * Helper class to manage app's dependencies.
 *
 * @author dnieln7
 *
 * @param context Application context to initialize context dependencies.
 */
class ServiceLocator(context: Context) {

    private val api = Api("https://eventos-uv-api.herokuapp.com", "https://api.thecatapi.com/v1/")
    private val fakeDatabase = context.createFakeDatabase()

    val userAuthSource = UserRemoteAuthSource(api.userService)
    val userDataSource = UserLocalDataSource(fakeDatabase.userDao())

    val catApiDataSource = CatApiDataSource(api.catService)
    val catDbDataSource = CatDbDataSource(fakeDatabase.catDao())

    val catRepository = CatRepository(catDbDataSource, catApiDataSource)
}
