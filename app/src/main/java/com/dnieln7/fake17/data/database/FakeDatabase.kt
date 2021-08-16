package com.dnieln7.fake17.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dnieln7.fake17.data.database.converter.UserConverters
import com.dnieln7.fake17.data.database.dao.UserDao
import com.dnieln7.fake17.domain.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(UserConverters::class)
abstract class FakeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun Context.createFakeDatabase(): FakeDatabase {
            return Room.databaseBuilder(
                this,
                FakeDatabase::class.java,
                "fake-data"
            ).build()
        }
    }
}