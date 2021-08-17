package com.dnieln7.fake17.data.network

import com.dnieln7.fake17.data.network.cat.CatService
import com.dnieln7.fake17.data.network.user.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Container for all api services.
 *
 * @author dnieln7
 *
 * @param authBaseUrl Base url of the authentication server.
 * @param catsBaseUrl Base url of the cats server.
 */
class Api(authBaseUrl: String, catsBaseUrl: String) {

    private val okHttpClient = OkHttpClient.Builder().build()
    private val gsonConverterFactory = GsonConverterFactory.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(authBaseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val retrofitCat = Retrofit.Builder()
        .baseUrl(catsBaseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val userService: UserService = retrofit.create(UserService::class.java)
    val catService: CatService = retrofitCat.create(CatService::class.java)
}