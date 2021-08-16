package com.dnieln7.fake17.data.source.cat

import com.dnieln7.fake17.data.database.dao.CatDao
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CatDbDataSource(private val dao: CatDao) : CatLocalDataSource {

    override fun save(cats: List<Cat>): Single<List<Long>> {
        return dao.save(cats)
    }

    override fun getCats(): Observable<List<Cat>> {
        return dao.findAll()
    }

    override fun deleteAll(): Single<Int> {
        return dao.deleteAll()
    }
}