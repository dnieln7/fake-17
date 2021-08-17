package com.dnieln7.fake17.data.repository

import com.dnieln7.fake17.data.source.cat.CatLocalDataSource
import com.dnieln7.fake17.data.source.cat.CatRemoteDataSource
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable

/**
 * Repository for cats data.
 *
 * @author dnieln7
 *
 * @param catLocalDataSource Implementation of a [CatLocalDataSource]
 * @param catRemoteDataSource Implementation of a [CatRemoteDataSource]
 */
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

    fun getApiCats(limit: Int): Observable<List<Cat>>? {
        return catRemoteDataSource.getCats(limit).toObservable()
            .doOnNext { catLocalDataSource.save(it) }
    }
}