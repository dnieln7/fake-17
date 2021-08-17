package com.dnieln7.fake17.data.database.converter

import androidx.room.TypeConverter
import com.dnieln7.fake17.domain.User
import com.google.gson.Gson

/**
 *
 * Converters to save [User] unsupported data types.
 * @author dnieln7
 */
class UserConverters {

    /**
     * Converts a List of Strings to a JSON string.
     */
    @TypeConverter
    fun fromHobbies(value: List<String>): String {
        return Gson().toJson(value)
    }

    /**
     * Converts a JSON string to a List of Strings.
     */
    @TypeConverter
    fun toHobbies(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }
}