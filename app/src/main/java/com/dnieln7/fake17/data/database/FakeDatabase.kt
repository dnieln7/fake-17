package com.dnieln7.fake17.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dnieln7.fake17.data.database.converter.CatConverters
import com.dnieln7.fake17.data.database.converter.UserConverters
import com.dnieln7.fake17.data.database.dao.CatDao
import com.dnieln7.fake17.data.database.dao.UserDao
import com.dnieln7.fake17.domain.Cat
import com.dnieln7.fake17.domain.User

@Database(
    entities = [User::class, Cat::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(UserConverters::class, CatConverters::class)
abstract class FakeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun catDao(): CatDao

    companion object {

        /**
         * Migration to create a new table for [Cat] entity.
         */
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `tb_cats` (`id` TEXT NOT NULL, `affection_level` INTEGER NOT NULL, `child_friendly` INTEGER NOT NULL, `description` TEXT NOT NULL, `dog_friendly` INTEGER NOT NULL, `energy_level` INTEGER NOT NULL, `hairless` INTEGER NOT NULL, `hypoallergenic` INTEGER NOT NULL, `image` TEXT, `indoor` INTEGER, `life_span` TEXT NOT NULL, `name` TEXT NOT NULL, `origin` TEXT NOT NULL, `shedding_level` INTEGER NOT NULL, `social_needs` INTEGER NOT NULL, `stranger_friendly` INTEGER NOT NULL, `temperament` TEXT NOT NULL, `weight` TEXT NOT NULL, `wikipedia_url` TEXT, PRIMARY KEY(`id`))")
            }
        }

        /**
         * Helper method to create an instance of [FakeDatabase]
         */
        fun Context.createFakeDatabase(): FakeDatabase {
            return Room.databaseBuilder(
                this,
                FakeDatabase::class.java,
                "fake-data"
            ).addMigrations(MIGRATION_1_2).build()
        }
    }
}