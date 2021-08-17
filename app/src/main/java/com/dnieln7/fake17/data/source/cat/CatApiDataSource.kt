package com.dnieln7.fake17.data.source.cat

import com.dnieln7.fake17.data.network.cat.CatService
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Single

/**
 * Implementation of [CatRemoteDataSource] that uses Retrofit to fetch cat data.
 *
 * @author dnieln7
 *
 * @param service A [CatService] implementation to interact with the server.
 */
class CatApiDataSource(private val service: CatService) : CatRemoteDataSource {

    override fun getCats(limit: Int): Single<List<Cat>> {
        return service.getCats(limit)
    }
}