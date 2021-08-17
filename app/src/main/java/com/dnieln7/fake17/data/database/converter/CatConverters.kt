package com.dnieln7.fake17.data.database.converter

import androidx.room.TypeConverter
import com.dnieln7.fake17.domain.Cat
import com.dnieln7.fake17.domain.CatImage
import com.dnieln7.fake17.domain.CatWeight
import com.google.gson.Gson

/**
 * Converters to save [Cat] unsupported data types.
 *
 * @author dnieln7
 */
class CatConverters {

    /**
     * Converts a [CatImage] to a JSON string.
     * @param value The [CatImage] instance to be converted. If null a default one is provided.
     */
    @TypeConverter
    fun fromImage(value: CatImage?): String {
        return if (value != null) Gson().toJson(value) else Gson().toJson(CatImage())
    }

    /**
     * Converts a JSON string to a [CatImage].
     */
    @TypeConverter
    fun toImage(value: String): CatImage {
        return Gson().fromJson(value, CatImage::class.java)
    }

    /**
     * Converts a [CatWeight] to a JSON string.
     */
    @TypeConverter
    fun fromWeight(value: CatWeight): String {
        return Gson().toJson(value)
    }

    /**
     * Converts a JSON string to a [CatWeight].
     */
    @TypeConverter
    fun toWeight(value: String): CatWeight {
        return Gson().fromJson(value, CatWeight::class.java)
    }
}