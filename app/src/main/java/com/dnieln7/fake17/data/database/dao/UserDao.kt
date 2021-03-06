package com.dnieln7.fake17.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dnieln7.fake17.domain.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM tb_users ORDER BY id ASC LIMIT 1")
    fun getFirstUser(): Maybe<User>

    @Insert
    fun save(user: User): Single<Long>

    @Query("DELETE FROM tb_users")
    fun deleteAll(): Single<Int>
}