package com.dnieln7.fake17.data.source.cat

import com.dnieln7.fake17.data.database.dao.CatDao
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Implementation of [CatLocalDataSource] that uses Room to save and read cat data.
 *
 * @author dnieln7
 *
 * @param dao A [CatDao] implementation to interact with the database.
 */
class CatDbDataSource(private val dao: CatDao) : CatLocalDataSource {

    override fun save(cats: List<Cat>): List<Long> {
        return dao.save(cats)
    }

    override fun getCats(): Observable<List<Cat>> {
        return dao.findAll()
    }

    override fun deleteAll(): Single<Int> {
        return dao.deleteAll()
    }
}