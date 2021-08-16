package com.dnieln7.fake17.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class UserConverters {

    @TypeConverter
    fun fromHobbies(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toHobbies(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }
}