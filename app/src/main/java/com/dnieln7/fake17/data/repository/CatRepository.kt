package com.dnieln7.fake17.data.repository

import com.dnieln7.fake17.data.source.cat.CatLocalDataSource
import com.dnieln7.fake17.data.source.cat.CatRemoteDataSource
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CatRepository(
    private val catLocalDataSource: CatLocalDataSource,
    private val catRemoteDataSource: CatRemoteDataSource
) {

    fun getCats(limit: Int): Observable<List<Cat>> {
        return catLocalDataSource.getCats()
    }

    fun getDbCats():Observable<List<Cat>> {
        return catLocalDataSource.getCats()
    }

    fun getApiCats(limit: Int): Single<List<Cat>> {
        return catRemoteDataSource.getCats(limit)
            .doOnSuccess { catLocalDataSource.save(it) }
    }
}