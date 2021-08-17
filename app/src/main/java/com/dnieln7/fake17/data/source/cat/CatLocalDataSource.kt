package com.dnieln7.fake17.data.source.cat

import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CatLocalDataSource {
    fun save(cats: List<Cat>): List<Long>

    fun getCats(): Observable<List<Cat>>

    fun deleteAll(): Single<Int>
}