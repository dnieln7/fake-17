package com.dnieln7.fake17.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnieln7.fake17.domain.Cat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface CatDao {

    @Query("SELECT * FROM tb_cats")
    fun findAll(): Observable<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cats: List<Cat>): List<Long>

    @Query("DELETE FROM tb_cats")
    fun deleteAll(): Single<Int>
}