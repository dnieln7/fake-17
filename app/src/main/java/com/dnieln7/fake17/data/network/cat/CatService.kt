package com.dnieln7.fake17.data.network.cat

import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("breeds")
    fun getCats(@Query("limit") limit: Int): Single<List<Cat>>
}