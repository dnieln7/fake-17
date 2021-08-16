package com.dnieln7.fake17.data.source.cat

import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Single

interface CatRemoteDataSource {
    fun getCats(limit: Int): Single<List<Cat>>
}